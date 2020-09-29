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
package uk.gov.hmrc.components.organism.status

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.base.DynamicCardView
import uk.gov.hmrc.components.base.PaddedComponent
import uk.gov.hmrc.components.databinding.ComponentStatusCardBinding
import uk.gov.hmrc.components.extensions.setMargins
import uk.gov.hmrc.components.molecule.status.StatusView

open class StatusCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : DynamicCardView(context, attrs), PaddedComponent {

    private val binding: ComponentStatusCardBinding =
            ComponentStatusCardBinding.inflate(LayoutInflater.from(context), this, true)
    override val childContainer: LinearLayout = binding.layout

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.StatusCardView, 0, 0)
            val title = typedArray.getString(R.styleable.StatusCardView_title)
            val body = typedArray.getString(R.styleable.StatusCardView_body)
            val bodyContentDesc = typedArray.getString(R.styleable.StatusCardView_bodyContentDesc)
            val icon = typedArray.getResourceId(R.styleable.StatusCardView_icon, StatusView.NO_ICON)
            val iconTint = typedArray.getResourceId(R.styleable.StatusCardView_iconTintColor, StatusView.NO_ICON_TINT)

            setTitle(title)
            setBody(body)
            setBodyContentDesc(bodyContentDesc)
            setIcon(icon)
            setIconTintColor(iconTint)

            typedArray.recycle()
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.status.setTitle(title)
    }

    fun setBody(body: CharSequence?) {
        binding.status.setBody(body)
    }

    fun setBodyContentDesc(bodyContentDesc: CharSequence?) {
        binding.status.setBodyContentDesc(bodyContentDesc)
    }

    fun setBodyGravity(gravity: Int) {
        binding.status.setBodyGravity(gravity)
    }

    fun setIcon(@DrawableRes icon: Int) {
        binding.status.setIcon(icon)
    }

    fun setIconTintColor(@ColorRes iconTint: Int) {
        binding.status.setIconTintColor(iconTint)
    }

    override fun removeChildPadding() {
        val defaultPadding = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
        // Remove all but top padding on the layout
        childContainer.setPadding(0, defaultPadding, 0, 0)
        // Add side padding to the status view
        binding.status.setMargins(defaultPadding, 0, defaultPadding, 0)
    }
}
