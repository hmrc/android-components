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
package uk.gov.hmrc.components.organism.primary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.base.DynamicCardView
import uk.gov.hmrc.components.base.PaddedComponent
import uk.gov.hmrc.components.databinding.ComponentPrimaryCardBinding
import uk.gov.hmrc.components.extensions.setMargins

open class PrimaryCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : DynamicCardView(context, attrs), PaddedComponent {

    private val binding: ComponentPrimaryCardBinding =
        ComponentPrimaryCardBinding.inflate(LayoutInflater.from(context), this, true)
    override val childContainer: LinearLayout = binding.layout

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.PrimaryCardView, 0, 0)
            val title = typedArray.getString(R.styleable.PrimaryCardView_title)
            val titleContentDescription = typedArray.getString(R.styleable.PrimaryCardView_titleContentDescription)

            setTitle(title)
            setTitleContentDescription(titleContentDescription)
            typedArray.recycle()
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    fun setTitleContentDescription(desc: String?) {
        desc?.let { binding.title.contentDescription = it }
    }

    override fun removeChildPadding() {
        val defaultPadding = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
        // Remove all but top padding on the layout
        childContainer.setPadding(0, defaultPadding, 0, 0)
        // Add side padding to the title
        binding.title.setMargins(defaultPadding, 0, defaultPadding, 0)
    }
}
