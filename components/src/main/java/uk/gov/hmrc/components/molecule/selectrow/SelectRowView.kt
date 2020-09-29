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
package uk.gov.hmrc.components.molecule.selectrow

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentSelectRowBinding
import uk.gov.hmrc.components.extensions.addRipple

class SelectRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: ComponentSelectRowBinding =
            ComponentSelectRowBinding.inflate(LayoutInflater.from(context), this)

    private val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)

            updateContentDescription()
        }
    }

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                    it,
                    R.styleable.SelectRowView,
                    0,
                    0
            )
            val text = typedArray.getString(R.styleable.SelectRowView_text)
            val button = typedArray.getDrawable(R.styleable.SelectRowView_button)

            setText(text)
            setButton(button)

            typedArray.recycle()
        }

        setPadding(resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16),
                resources.getDimensionPixelSize(R.dimen.hmrc_spacing_4),
                resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16),
                resources.getDimensionPixelSize(R.dimen.hmrc_spacing_4))

        setOnClickListener {
            binding.selectRowRadioButton.isChecked = true
            announceForAccessibility(
                    "${context.getString(R.string.accessibility_ticked)}, ${binding.selectRowBody.text}")
        }

        addRipple()

        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    fun setButton(button: Drawable?) {
        button?.let {
            binding.selectRowRadioButton.buttonDrawable = button
        }
    }

    fun setText(text: CharSequence?) {
        binding.selectRowBody.text = text
    }

    internal fun updateContentDescription() {
        val selectRowOptions = (parent as LinearLayout)
        var positionInGroup = -1

        (0..selectRowOptions.childCount).forEach {
            if (selectRowOptions.getChildAt(it) == this) positionInGroup = it + 1
        }

        val positionDescription = context.getString(
                R.string.accessibility_list_position,
                positionInGroup,
                selectRowOptions.childCount
        )
        val tickedState = context.getString(
                if (binding.selectRowRadioButton.isChecked) {
                    R.string.accessibility_ticked
                } else {
                    R.string.accessibility_not_ticked
                }
        )
        contentDescription = "$tickedState," +
                " ${binding.selectRowBody.text}," +
                " ${context.getString(R.string.accessibility_radio_button)}." +
                " $positionDescription"
    }
}
