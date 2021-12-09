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
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.BASELINE
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentMenuPanelRowBinding

class MenuPanelRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private val binding: ComponentMenuPanelRowBinding =
        ComponentMenuPanelRowBinding.inflate(LayoutInflater.from(context), this, true)

    private val isLargeText: Boolean
        get() = resources.configuration.fontScale > MAX_SINGLE_LINE_FONT_SCALE

    private var menuPanelNotification: Notification = Notification.None

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.MenuPanelRowView, 0, 0)
            val title = typedArray.getString(R.styleable.MenuPanelRowView_title) ?: ""
            val body = typedArray.getString(R.styleable.MenuPanelRowView_body) ?: ""

            setTitle(title)
            setBody(body)
            typedArray.recycle()
        }
        if (isLargeText) adjustLayoutForBigText()
        setBackgroundColor(ContextCompat.getColor(context, R.color.hmrc_grey_3))
        setRippleColorResource(R.color.hmrc_secondary_button_ripple)
    }

    @Suppress("ComplexMethod")
    private fun updateMenuPanelContentDescription() {
        val title = getTitle()
        val body = getBody()
        val notification = menuPanelNotification
        binding.layout.apply {
            contentDescription = when {
                notification is Notification.Count && notification.count == 0 -> {
                    context.getString(
                        R.string.menu_panel_button_circle_notification_content_description,
                        title,
                        body
                    )
                }
                notification is Notification.Count && notification.count == 1 -> {
                    context.getString(
                        R.string.menu_panel_button_one_notification_content_description,
                        title,
                        notification.count.toString(),
                        body
                    )
                }
                notification is Notification.Count && notification.count > 1 -> {
                    context.getString(
                        R.string.menu_panel_button_multiple_notifications_content_description,
                        title,
                        notification.count.toString(),
                        body
                    )
                }
                notification is Notification.New -> {
                    context.getString(
                        R.string.menu_panel_button_new_notification_content_description,
                        title,
                        body
                    )
                }
                else -> context.getString(R.string.menu_panel_button_no_notification_content_description, title, body)
            }
        }
    }

    fun setTitle(title: CharSequence) {
        binding.textTitle.text = title
        updateMenuPanelContentDescription()
    }

    fun getTitle(): CharSequence {
        return binding.textTitle.text
    }

    fun setBody(body: CharSequence) {
        binding.textBody.text = body
        updateMenuPanelContentDescription()
    }

    fun getBody(): CharSequence {
        return binding.textBody.text
    }

    fun showNotification(notificationType: Notification = Notification.Count()) {
        menuPanelNotification = notificationType
        binding.notification.apply {
            text = when (notificationType) {
                is Notification.New -> context.getString(R.string.menu_panel_button_new_notification)
                is Notification.Count -> {
                    when {
                        notificationType.count > MAXIMUM_NOTIFICATION_COUNT -> {
                            context.getString(R.string.menu_panel_button_over_ninety_nine_notifications_text)
                        }
                        notificationType.count > 0 -> notificationType.count.toString()
                        else -> null
                    }
                }
                is Notification.None -> null
            }
            setNotificationBackground(this)
            updateMenuPanelContentDescription()
            visibility = View.VISIBLE
        }
    }

    private fun setNotificationBackground(notificationView: TextView) {
        val notification = menuPanelNotification
        notificationView.apply {
            if (notification is Notification.Count && notification.count == 0) {
                background = ContextCompat.getDrawable(context, R.drawable.components_menu_pane_circle_notification)
                (this.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio = "1:1"
            } else {
                background =
                    ContextCompat.getDrawable(context, R.drawable.components_menu_panel_round_square_notification)
                (this.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio = "2:1"
            }
        }
    }

    fun hideNotification() {
        binding.notification.visibility = View.GONE
        updateMenuPanelContentDescription()
    }

    private fun adjustLayoutForBigText() {
        val defaultMargin = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_8)
        ConstraintSet().apply {
            clone(binding.layout)
            clear(R.id.text_title, END)
            connect(R.id.text_title, END, R.id.image_chevron, START)
            clear(R.id.text_body, TOP)
            connect(R.id.text_body, TOP, R.id.notification, BOTTOM, defaultMargin)
            clear(R.id.notification, BASELINE)
            clear(R.id.notification, END)
            clear(R.id.notification, START)
            connect(R.id.notification, START, PARENT_ID, START)
            connect(R.id.notification, TOP, R.id.text_title, BOTTOM, defaultMargin)
            applyTo(binding.layout)
        }
    }

    sealed class Notification {
        data class Count(val count: Int = 0) : Notification()
        object New : Notification()
        object None : Notification()
    }

    companion object {
        const val MAXIMUM_NOTIFICATION_COUNT = 99
        private const val MAX_SINGLE_LINE_FONT_SCALE = 1.1
    }
}
