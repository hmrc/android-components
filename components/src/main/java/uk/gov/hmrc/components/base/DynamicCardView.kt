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
package uk.gov.hmrc.components.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import uk.gov.hmrc.components.R

abstract class DynamicCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    var childPadding: Boolean = DEFAULT_CHILD_PADDING

    init {
        attrs?.let { it ->
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.DynamicCardView, 0, 0)
            childPadding = typedArray.getBoolean(R.styleable.DynamicCardView_childPadding, DEFAULT_CHILD_PADDING)
            typedArray.recycle()
        }
    }

    abstract val childContainer: LinearLayout

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        val childIndex = indexOfChild(child)
        when (childIndex) {
            0 -> {
                // This is the card view, we should not react to this being added
            }
            else -> {
                // A child view has been added, we should move this into the child view container
                val parent = (child?.parent as ViewGroup)
                parent.removeView(child)
                childContainer.addView(child, getLinearLayoutParams(child))
                (parent as? PaddedComponent)?.adjustPaddingForChildren()
                if (!childPadding) {
                    (parent as? PaddedComponent)?.removeChildPadding()
                }
            }
        }
    }

    private fun getLinearLayoutParams(view: View): LinearLayout.LayoutParams? {
        val layoutParams = view.layoutParams as? ViewGroup.MarginLayoutParams
        return layoutParams?.let {
            LinearLayout.LayoutParams(it.width, it.height).apply {
                setMargins(it.marginStart, it.topMargin, it.rightMargin, it.bottomMargin)
            }
        }
    }

    companion object {
        const val DEFAULT_CHILD_PADDING = true
    }
}
