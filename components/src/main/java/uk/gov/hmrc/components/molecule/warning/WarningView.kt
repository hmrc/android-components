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
package uk.gov.hmrc.components.molecule.warning

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentWarningBinding

class WarningView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val binding: ComponentWarningBinding =
        ComponentWarningBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.WarningView, 0, 0)
            val text = typedArray.getString(R.styleable.WarningView_text)
            val textColor = typedArray.getResourceId(R.styleable.WarningView_textColor, R.color.hmrc_black)
            val icon = typedArray.getResourceId(R.styleable.WarningView_icon, R.drawable.components_ic_warning)
            val iconTint = typedArray.getResourceId(R.styleable.WarningView_iconTintColor, R.color.hmrc_black)
            val defaultPadding = typedArray.getBoolean(R.styleable.WarningView_defaultPadding, true)

            setText(text)
            setTextColor(textColor)
            setIcon(icon)
            setIconTintColor(iconTint)
            if (defaultPadding) {
                // Component has default padding of 8dp
                resources.getDimensionPixelSize(R.dimen.hmrc_spacing_8).let { setPadding(it, it, it, it) }
            }

            typedArray.recycle()
        }
    }

    fun getText(): CharSequence? = binding.text.text

    fun setText(inputText: CharSequence?) {
        binding.text.text = inputText
        contentDescription = context.getString(R.string.accessibility_warning, inputText.toString())
    }

    fun setText(@StringRes id: Int) = setText(context.getString(id))

    fun updateContentDescription(textContentDescription: String) {
        contentDescription = textContentDescription
    }

    fun setTextColor(@ColorRes textColor: Int) {
        binding.text.setTextColor(ContextCompat.getColor(context, textColor))
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

    companion object {
        const val NO_ICON: Int = -1
        const val NO_ICON_TINT: Int = -1
    }
}
