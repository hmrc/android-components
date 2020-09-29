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
package uk.gov.hmrc.components.molecule.inset

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.base.PaddedComponent
import uk.gov.hmrc.components.databinding.ComponentInsetBinding
import uk.gov.hmrc.components.extensions.setMargins

open class InsetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes), PaddedComponent {

    private val binding: ComponentInsetBinding =
            ComponentInsetBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let { it ->
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.InsetView, 0, 0)
            val childPadding = typedArray.getBoolean(R.styleable.InsetView_childPadding, true)

            if (!childPadding) removeChildPadding()

            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        // Moves any inflated views inside InsetView into inset_view_container
        // so the divider has the same height as the extra content
        while (getChildAt(DYNAMIC_VIEWS_STARTING_CHILD_INDEX) != null) {
            val child = getChildAt(DYNAMIC_VIEWS_STARTING_CHILD_INDEX)
            removeView(child)
            binding.insetViewContainer.addView(child)
        }
    }

    override fun removeChildPadding() {
        binding.insetViewContainer.setMargins(0, 0, 0, 0)
    }

    companion object {
        const val DYNAMIC_VIEWS_STARTING_CHILD_INDEX = 2
    }
}
