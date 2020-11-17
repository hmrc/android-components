/*
 * Copyright 2020 HM Revenue & Customs
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
package uk.gov.hmrc.components.organism.menu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.base.PaddedComponent
import uk.gov.hmrc.components.databinding.ComponentMenuPanelRowBinding
import uk.gov.hmrc.components.extensions.addRipple
import uk.gov.hmrc.components.extensions.setMargins

class MenuPanelRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), PaddedComponent {

    private val binding: ComponentMenuPanelRowBinding =
            ComponentMenuPanelRowBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.MenuPanelRowView, 0, 0)
            val title = typedArray.getString(R.styleable.MenuPanelRowView_title) ?: ""
            val body = typedArray.getString(R.styleable.MenuPanelRowView_body) ?: ""

            setTitle(title)
            setBody(body)
            typedArray.recycle()
        }
        addRipple()
    }

    fun setTitle(title: CharSequence?) {
        binding.textTitle.text = title
    }

    fun setBody(body: CharSequence?) {
        binding.textBody.text = body
    }

    fun setNotification(body: String? = null) {
        binding.notification.apply {
            body?.let {
                text = body
                background = context.getDrawable(R.drawable.components_menu_panel_long_notification)
            } ?: run {
                background = context.getDrawable(R.drawable.components_menu_panel_dot_notification)
                layoutParams.width = 0
            }
            visibility = View.VISIBLE
        }
    }

    fun removeNotification() {
        binding.notification.visibility = View.GONE
    }

    override fun removeChildPadding() {
        val defaultPadding = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_24)
        val leftRightPadding = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)

        binding.textTitle.setMargins(leftRightPadding, defaultPadding, leftRightPadding, defaultPadding)
        binding.textBody.setMargins(leftRightPadding, 0, leftRightPadding, defaultPadding)
    }
}
