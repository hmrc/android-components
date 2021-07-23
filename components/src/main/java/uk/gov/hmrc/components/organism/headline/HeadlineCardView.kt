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
package uk.gov.hmrc.components.organism.headline

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.children
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.base.DynamicCardView
import uk.gov.hmrc.components.base.PaddedComponent
import uk.gov.hmrc.components.databinding.ComponentHeadlineCardBinding
import uk.gov.hmrc.components.extensions.setMargins

open class HeadlineCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : DynamicCardView(context, attrs), PaddedComponent {

    private val binding: ComponentHeadlineCardBinding =
            ComponentHeadlineCardBinding.inflate(LayoutInflater.from(context), this, true)
    override val childContainer: LinearLayout = binding.headlineLayout

    init {
        attrs?.let { it ->
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.HeadlineCardView, 0, 0)
            val headline = typedArray.getString(R.styleable.HeadlineCardView_headline)
            val headlineContentDescription = typedArray.getString(
                    R.styleable.HeadlineCardView_headlineContentDescription
            )
            val title = typedArray.getString(R.styleable.HeadlineCardView_title)
            val titleContentDescription = typedArray.getString(R.styleable.HeadlineCardView_titleContentDescription)

            setHeadline(headline)
            setHeadlineContentDescription(headlineContentDescription)
            setTitle(title)
            setTitleContentDescription(titleContentDescription)

            typedArray.recycle()
        }
    }

    fun setHeadline(headline: CharSequence?) {
        binding.headline.text = headline
    }

    fun setHeadlineContentDescription(desc: String?) {
        desc?.let { binding.headline.contentDescription = it }
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    fun setTitleContentDescription(desc: String?) {
        desc?.let { binding.title.contentDescription = it }
    }

    fun setOnClickListener(clickHandler: () -> Unit) {
        setRippleColorResource(R.color.hmrc_secondary_button_ripple)
        super.setOnClickListener {
            clickHandler.invoke()
        }
        ensureChildViewsAreFocusable()
        binding.imageChevron.visibility = View.VISIBLE
    }

    // This method can be used as an accessibility aid for informing talkback users of the result of tapping the view.
    fun setChevronContentDescription(description: CharSequence) {
        binding.imageChevron.contentDescription = description
    }

    private fun ensureChildViewsAreFocusable() {
        childContainer.children.forEach { childView -> childView.isFocusable = true }
    }

    override fun removeChildPadding() {
        val defaultOuterPadding = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
        val defaultInnerVerticalPadding = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_24)
        // Remove all but top padding on the layout
        childContainer.setPadding(0, defaultOuterPadding, 0, 0)
        binding.apply {
            // Add side and bottom margins to the title
            title.setMargins(defaultOuterPadding, 0, defaultOuterPadding, defaultInnerVerticalPadding)
            // Add side and bottom margins to the headline
            headline.setMargins(defaultOuterPadding, 0, defaultOuterPadding, defaultInnerVerticalPadding)
        }
    }
}
