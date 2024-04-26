/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.components.organism.summary

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentSummaryRowBinding
import uk.gov.hmrc.components.extensions.addRipple
import uk.gov.hmrc.components.extensions.setAccessibilityMessage
import uk.gov.hmrc.components.molecule.item.MultiColumnRowView

class SummaryRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding: ComponentSummaryRowBinding =
        ComponentSummaryRowBinding.inflate(LayoutInflater.from(context), this)

    var readerTrait = READER_TRAIT_INFO
        set(value) {
            field = value

            when (value) {
                READER_TRAIT_INFO -> enableFocusOnChildViews(true)
                READER_TRAIT_SIMPLE -> enableFocusOnChildViews(false)
            }
        }

    var rowStyle = R.style.Text_Info
        set(value) {
            field = value
            (0..binding.rowContainer.childCount)
                .map { binding.rowContainer.getChildAt(it) }
                .filterIsInstance<MultiColumnRowView>()
                .forEach { it.setTextStyle(rowStyle) }
        }

    init {
        var icon = R.drawable.components_ic_chevron_right
        var iconTint = R.color.hmrc_black

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.SummaryRowView, 0, 0)
            val titleText = typedArray.getString(R.styleable.SummaryRowView_title)
            val titleTextAppearance = typedArray.getResourceId(
                R.styleable.SummaryRowView_titleTextAppearance,
                R.style.Text_Bold
            )
            icon =
                typedArray.getResourceId(R.styleable.SummaryRowView_icon, R.drawable.components_ic_chevron_right)
            iconTint = typedArray.getResourceId(R.styleable.SummaryRowView_iconTintColor, R.color.hmrc_black)

            val titleMaxLines = typedArray.getInt(R.styleable.SummaryRowView_titleMaxLines, -1)
            val accessibilityMessage = typedArray.getString(R.styleable.SummaryRowView_accessibilityMessage)
            val readerTrait = typedArray.getInt(R.styleable.SummaryRowView_readerTrait, READER_TRAIT_INFO)
            rowStyle = typedArray.getInt(R.styleable.SummaryRowView_rowStyle, R.style.Text_Info)

            setTitle(titleText)
            setTitleTextAppearance(titleTextAppearance)
            setTitleMaxLines(titleMaxLines)
            accessibilityMessage?.let { message ->
                setAccessibilityMessage(message)
            }

            this.readerTrait = readerTrait

            typedArray.recycle()
        }

        resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16).let {
            setPadding(it, it, it, it)
        }
        setIcon(icon)
        setIconTintColor(iconTint)
        addRipple()
    }

    fun getTitle(): CharSequence? {
        return binding.textTitle.text
    }

    fun setTitle(title: CharSequence?) {
        binding.textTitle.text = title
    }

    fun setTitleTextAppearance(@StyleRes id: Int) {
        TextViewCompat.setTextAppearance(binding.textTitle, id)
    }

    fun setTitleMaxLines(maxLines: Int) {
        if (maxLines < 1) return
        binding.textTitle.apply {
            this.maxLines = maxLines
            ellipsize = TextUtils.TruncateAt.END
        }
    }

    fun setRows(rows: MutableList<MultiColumnRowView>) {
        binding.rowContainer.removeAllViews()
        for (row in rows) {
            row.setTextStyle(rowStyle)
            row.setPadding(0, resources.getDimensionPixelOffset(R.dimen.hmrc_spacing_8), 0, 0)

            when {
                row.hasCustomWholeRowDescription -> row.isFocusable = true
                readerTrait == READER_TRAIT_INFO -> row.setTextFocusable(true)
                readerTrait == READER_TRAIT_SIMPLE -> row.setTextFocusable(false)
            }

            binding.rowContainer.addView(row)
        }
    }

    fun setIcon(@DrawableRes icon: Int) {
        binding.imageChevron.setImageResource(icon)
    }

    fun setIconTintColor(@ColorRes iconTint: Int) {
        binding.imageChevron.setColorFilter(
            ContextCompat.getColor(context, iconTint),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    fun setOnClickListener(clickHandler: () -> Unit) {
        isClickable = true
        isFocusable = true
        super.setOnClickListener {
            clickHandler.invoke()
        }
        binding.imageChevron.visibility = View.VISIBLE
    }

    fun setButtonAccessibilityMessage(buttonText: String, action: String) {
        contentDescription = context.getString(R.string.button_content_description, buttonText)
        setAccessibilityMessage(action)
    }

    // This method can be used as an accessibility aid for informing talkback users of the result of tapping the view.
    fun setChevronContentDescription(description: CharSequence) {
        binding.imageChevron.contentDescription = description
    }

    private fun enableFocusOnChildViews(enable: Boolean) {
        binding.textTitle.isFocusable = enable
        binding.imageChevron.apply {
            isFocusable = enable
            importantForAccessibility = if (enable) {
                View.IMPORTANT_FOR_ACCESSIBILITY_YES
            } else {
                View.IMPORTANT_FOR_ACCESSIBILITY_NO
            }
        }
    }

    companion object {
        const val READER_TRAIT_INFO = 1
        const val READER_TRAIT_SIMPLE = 2
    }
}
