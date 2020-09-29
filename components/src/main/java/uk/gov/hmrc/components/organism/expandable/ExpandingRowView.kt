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
package uk.gov.hmrc.components.organism.expandable

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentExpandingRowBinding
import uk.gov.hmrc.components.extensions.setAccessibilityMessage

class ExpandingRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var expanded = false
        set(value) {
            field = value
            updateAccessibilityMessage(field)
            updateExpandIconIndicator(field)
        }
    private var contentView: View? = null
    private var expandedHeight: Int = 0
    private var animating: Boolean = false

    private var listeners: ArrayList<(isExpanded: Boolean) -> Unit> = ArrayList()
    private val binding: ComponentExpandingRowBinding =
            ComponentExpandingRowBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.ExpandingRowView, 0, 0)
            val title = typedArray.getString(R.styleable.ExpandingRowView_title)
            val titleContentDescription = typedArray.getString(R.styleable.ExpandingRowView_titleContentDescription)
            val subtitle = typedArray.getString(R.styleable.ExpandingRowView_subtitle)
            val subtitleContentDescription = typedArray.getString(
                    R.styleable.ExpandingRowView_subtitleContentDescription
            )
            val icon = typedArray.getResourceId(R.styleable.ExpandingRowView_icon, NO_ICON)
            val clickTogglesExpansion = typedArray.getBoolean(R.styleable.ExpandingRowView_clickTogglesExpansion, false)
            expanded = typedArray.getBoolean(R.styleable.ExpandingRowView_expanded, false)

            setTitle(title)
            setTitleContentDescription(titleContentDescription)
            setSubtitle(subtitle)
            setSubtitleContentDescription(subtitleContentDescription)
            setIcon(icon)
            setClickTogglesExpansion(clickTogglesExpansion)
            typedArray.recycle()
        }

        makeAdjustmentsForFontScale()
    }

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        if (childCount > 2)
        // More than one content view found. There should only be one direct child
            throw IllegalStateException("ExpandingRowView can host only one direct child")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (!animating && childCount == 2) {
            contentView = getChildAt(1)
            contentView?.let {
                if (it.measuredHeight > 0) {
                    expandedHeight = it.measuredHeight
                }

                if (!expanded) {
                    post {
                        it.visibility = GONE
                        it.layoutParams.height = 0
                    }
                }
            }
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.expandableTitle.text = title
    }

    fun setTitleContentDescription(desc: String?) {
        desc?.let { binding.expandableTitle.contentDescription = it }
    }

    fun setSubtitle(subtitle: CharSequence?) {
        if (subtitle.isNullOrBlank()) {
            binding.expandableSubtitle.visibility = View.GONE
        } else {
            binding.expandableSubtitle.apply {
                visibility = View.VISIBLE
                text = subtitle
            }
        }
    }

    fun setSubtitleContentDescription(desc: String?) {
        desc?.let { binding.expandableSubtitle.contentDescription = it }
    }

    fun setIcon(@DrawableRes icon: Int) {
        if (icon == NO_ICON) {
            binding.expandableIcon.visibility = View.GONE
        } else {
            binding.expandableIcon.apply {
                visibility = View.VISIBLE
                setImageDrawable(context.getDrawable(icon))
            }
        }
    }

    fun setClickTogglesExpansion(togglesExpansion: Boolean) {
        if (togglesExpansion) {
            setOnClickListener {
                onClick()
            }
        } else {
            binding.expandableHeader.setOnClickListener {
                onClick()
            }
        }
    }

    private fun onClick() {
        listeners.forEach {
            it.invoke(!expanded)
        }
        announceExpansion(!expanded)
        setContentVisibility(!expanded)
    }

    private fun announceExpansion(expanded: Boolean) {
        val message = if (expanded) {
            context.getString(R.string.accessibility_expanded)
        } else {
            context.getString(R.string.accessibility_collapsed)
        }

        announceForAccessibility(message)
    }

    private fun updateAccessibilityMessage(expanded: Boolean) {
        val message = if (expanded) {
            context.getString(R.string.accessibility_collapse)
        } else {
            context.getString(R.string.accessibility_expand)
        }
        binding.expandableHeader.setAccessibilityMessage(message)
    }

    private fun updateExpandIconIndicator(expanded: Boolean) {
        val stateSet = intArrayOf(android.R.attr.state_checked * if (expanded) 1 else -1)
        binding.expandableIndicator.setImageState(stateSet, true)
    }

    // do not call in layout pass as this method calls requestLayout
    private fun setContentVisibility(visible: Boolean, withAnimation: Boolean = true) {
        updateExpandIconIndicator(visible)
        updateAccessibilityMessage(visible)

        contentView?.let {
            val oldHeight = if (!visible) expandedHeight else 0
            val newHeight = if (visible) expandedHeight else 0
            if (withAnimation) {
                animating = true
                LinearRevealAnimation(it, oldHeight, newHeight)
                        .withDuration(resources.getInteger(R.integer.expandable_animation_duration).toLong())
                        .start(this) { expanded = visible; animating = false }
            } else {
                this.layoutParams?.let {
                    it.height = newHeight
                }
                requestLayout()
                expanded = visible
            }
        }
    }

    fun addExpansionListener(listener: (isExpanded: Boolean) -> Unit) {
        listeners.add(listener)
    }

    fun removeExpansionListener(listener: (isExpanded: Boolean) -> Unit) {
        listeners.remove(listener)
    }

    fun clearExpansionListeners() {
        listeners.clear()
    }

    /**
     * show subtitle below title if font is scaled
     */
    private fun makeAdjustmentsForFontScale() {
        val isMultiRow = resources.configuration.fontScale > MAX_FONT_SCALE

        binding.expandableTitleLayout.orientation = if (isMultiRow) {
            VERTICAL
        } else {
            HORIZONTAL
        }

        val titleLayoutMarginEnd = if (isMultiRow) {
            0
        } else {
            context.resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
        }

        val titleLayoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, titleLayoutMarginEnd, 0)
        }

        binding.expandableTitle.layoutParams = titleLayoutParams

        val subtitleLayoutWidth = if (isMultiRow) {
            LayoutParams.WRAP_CONTENT
        } else {
            0
        }

        binding.expandableSubtitle.apply {
            layoutParams = LayoutParams(subtitleLayoutWidth, LayoutParams.WRAP_CONTENT, 1.0F)
            gravity = getExpandableSubtitleGravity(isMultiRow)
        }
    }

    private fun getExpandableSubtitleGravity(isMultiRow: Boolean): Int {
        return if (isMultiRow) {
            Gravity.START
        } else {
            Gravity.END
        }
    }

    companion object {
        const val NO_ICON = -1
        private const val MAX_FONT_SCALE = 1.5
    }
}
