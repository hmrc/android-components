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
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.base.PaddedComponent
import uk.gov.hmrc.components.databinding.ComponentMenuPanelRowBinding
import uk.gov.hmrc.components.extensions.setMargins

class MenuPanelRowView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : MaterialCardView(context, attrs), PaddedComponent {

    private val binding: ComponentMenuPanelRowBinding =
            ComponentMenuPanelRowBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.MenuPanelRowView, 0, 0)
            val title = typedArray.getString(R.styleable.MenuPanelRowView_title) ?: ""
            val body = typedArray.getString(R.styleable.MenuPanelRowView_body) ?: ""

            setTitle(title)
            setBody(body)
            binding.layout.contentDescription =
                    context.getString(R.string.menu_panel_button_no_notification_content_description, title, body)
            typedArray.recycle()
        }
        setBackgroundColor(ContextCompat.getColor(context, R.color.hmrc_grey_3))
    }

    private fun updateContentDescription(notificationCount: Int) {
        val title = getTitle()
        val body = getBody()
        binding.layout.apply {
            contentDescription = when (notificationCount) {
                0 -> {
                    context.getString(R.string.menu_panel_button_circle_notification_content_description,
                            title,
                            body)
                }
                1 -> {
                    context.getString(R.string.menu_panel_button_one_notification_content_description,
                            title,
                            notificationCount.toString(),
                            body)
                }
                in 2..99 -> {
                    context.getString(R.string.menu_panel_button_multiple_notifications_content_description,
                            title,
                            notificationCount.toString(),
                            body)
                }
                else -> {
                    context.getString(R.string.menu_panel_button_over_ninety_nine_notifications_content_description,
                            title,
                            body)
                }
            }
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.textTitle.text = title
    }

    fun getTitle(): CharSequence? {
        return binding.textTitle.text
    }

    fun setBody(body: CharSequence?) {
        binding.textBody.text = body
    }

    fun getBody(): CharSequence? {
        return binding.textBody.text
    }

    fun setNotification(notificationCount: Int = 0) {
        binding.notification.apply {
            if (notificationCount > 0) {
                text = if (notificationCount > 99) {
                    context.getString(R.string.menu_panel_button_over_ninety_nine_notifications_text)
                } else {
                    notificationCount.toString()
                }
                background = context.getDrawable(R.drawable.components_menu_panel_round_square_notification)
                updateContentDescription(notificationCount)
            } else {
                background = context.getDrawable(R.drawable.components_menu_pane_circle_notification)
                layoutParams.width = 0
                updateContentDescription(0)
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
