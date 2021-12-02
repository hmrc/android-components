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
package uk.gov.hmrc.components.molecule.status

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentStatusBinding

class StatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val binding: ComponentStatusBinding =
        ComponentStatusBinding.inflate(LayoutInflater.from(context), this)

    var onPrimaryButtonClickedListener: () -> Unit = {}
    var onSecondaryButtonClickedListener: () -> Unit = {}

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.StatusView, 0, 0)
            val title = typedArray.getString(R.styleable.StatusView_title)
            val body = typedArray.getString(R.styleable.StatusView_body)
            val bodyContentDesc = typedArray.getString(R.styleable.StatusView_bodyContentDesc)
            val textColor = typedArray.getResourceId(R.styleable.StatusView_textColor, R.color.hmrc_black)
            val icon = typedArray.getResourceId(R.styleable.StatusView_icon, NO_ICON)
            val iconTint = typedArray.getResourceId(R.styleable.StatusView_iconTintColor, NO_ICON_TINT)
            val primaryButtonText = typedArray.getString(R.styleable.StatusView_primaryButtonText)
            val secondaryButtonText = typedArray.getString(R.styleable.StatusView_secondaryButtonText)
            val infoText = typedArray.getString(R.styleable.StatusView_infoText)

            setTitle(title)
            setBody(body)
            setBodyContentDesc(bodyContentDesc)
            setTextColor(textColor)
            setIcon(icon)
            setIconTintColor(iconTint)
            setPrimaryButtonText(primaryButtonText)
            setSecondaryButtonText(secondaryButtonText)
            setInfoText(infoText)

            typedArray.recycle()
        }

        orientation = VERTICAL
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    fun setBody(body: CharSequence?) {
        if (body.isNullOrEmpty()) {
            binding.body.visibility = GONE
        } else {
            binding.body.visibility = View.VISIBLE
            binding.body.text = body
        }
    }

    fun setBodyContentDesc(bodyContentDesc: CharSequence?) {
        bodyContentDesc?.let { binding.body.contentDescription = it }
    }

    fun setTextColor(@ColorRes textColor: Int) {
        binding.title.setTextColor(ContextCompat.getColor(context, textColor))
        binding.body.setTextColor(ContextCompat.getColor(context, textColor))
    }

    fun setBodyGravity(gravity: Int) {
        binding.body.gravity = gravity
    }

    fun setIcon(@DrawableRes icon: Int) {
        if (icon != NO_ICON) {
            binding.icon.visibility = View.VISIBLE
            binding.icon.setImageResource(icon)
        } else {
            binding.icon.visibility = View.GONE
        }
    }

    fun setIconTintColor(@ColorRes iconTint: Int) {
        if (iconTint != NO_ICON_TINT) {
            binding.icon.setColorFilter(
                ContextCompat.getColor(context, iconTint),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
    }

    fun setInfoText(infoText: String?) {
        infoText?.let {
            binding.info.apply {
                text = it
                visibility = View.VISIBLE
            }
        }
    }

    fun setPrimaryButtonText(primaryButtonText: String?) {
        primaryButtonText?.let {
            binding.primaryButton.apply {
                text = it
                visibility = View.VISIBLE
                setOnClickListener { onPrimaryButtonClickedListener.invoke() }
            }
        }
    }

    fun setSecondaryButtonText(secondaryButtonText: String?) {
        secondaryButtonText?.let {
            binding.secondaryButton.apply {
                text = it
                visibility = View.VISIBLE
                setOnClickListener { onSecondaryButtonClickedListener.invoke() }
            }
        }
    }

    companion object {
        const val NO_ICON: Int = -1
        const val NO_ICON_TINT: Int = -1
    }
}
