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
import uk.gov.hmrc.components.databinding.ComponentMenuPanelRowBinding

class MenuPanelRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private val binding: ComponentMenuPanelRowBinding =
            ComponentMenuPanelRowBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.MenuPanelRowView, 0, 0)
            val title = typedArray.getString(R.styleable.MenuPanelRowView_title) ?: ""
            val body = typedArray.getString(R.styleable.MenuPanelRowView_body) ?: ""

            setTitle(title)
            setBody(body)
            setDefaultContentDescription(title, body)
            typedArray.recycle()
        }
        setBackgroundColor(ContextCompat.getColor(context, R.color.hmrc_grey_3))
    }

    private fun setDefaultContentDescription(title: CharSequence, body: CharSequence) {
        binding.layout.contentDescription =
                context.getString(R.string.menu_panel_button_no_notification_content_description, title, body)
    }

    private fun setNotificationContentDescription(countToDisplay: Int) {
        val title = getTitle()
        val body = getBody()
        binding.layout.apply {
            contentDescription = when (countToDisplay) {
                0 -> {
                    context.getString(R.string.menu_panel_button_circle_notification_content_description,
                            title,
                            body)
                }
                1 -> {
                    context.getString(R.string.menu_panel_button_one_notification_content_description,
                            title,
                            countToDisplay.toString(),
                            body)
                }
                else -> {
                    val quantity = if (countToDisplay in 2..MAXIMUM_NOTIFICATION_COUNT) {
                        countToDisplay.toString()
                    } else {
                        context.getString(R.string.menu_panel_button_over_ninety_nine_notifications_text)
                    }
                    context.getString(R.string.menu_panel_button_multiple_notifications_content_description,
                            title,
                            quantity,
                            body)
                }
            }
        }
    }

    fun setTitle(title: CharSequence) {
        binding.textTitle.text = title
    }

    fun getTitle(): CharSequence {
        return binding.textTitle.text
    }

    fun setBody(body: CharSequence) {
        binding.textBody.text = body
    }

    fun getBody(): CharSequence {
        return binding.textBody.text
    }

    fun showNotification(notificationCount: Int = 0) {
        binding.notification.apply {
            if (notificationCount > 0) {
                text = if (notificationCount > MAXIMUM_NOTIFICATION_COUNT) {
                    context.getString(R.string.menu_panel_button_over_ninety_nine_notifications_text)
                } else {
                    notificationCount.toString()
                }
                background = context.getDrawable(R.drawable.components_menu_panel_round_square_notification)
                setNotificationContentDescription(notificationCount)
            } else {
                background = context.getDrawable(R.drawable.components_menu_pane_circle_notification)
                layoutParams.width = 0
                setNotificationContentDescription(0)
            }
            visibility = View.VISIBLE
        }
    }

    fun hideNotification() {
        binding.notification.visibility = View.GONE
        setDefaultContentDescription(getTitle(), getBody())
    }

    companion object {
        const val MAXIMUM_NOTIFICATION_COUNT = 99
    }
}
