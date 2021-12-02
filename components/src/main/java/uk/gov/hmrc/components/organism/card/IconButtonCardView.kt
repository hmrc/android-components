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
package uk.gov.hmrc.components.organism.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import com.google.android.material.card.MaterialCardView
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentIconButtonCardBinding
import uk.gov.hmrc.components.extensions.setAccessibilityMessage

class IconButtonCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private val binding: ComponentIconButtonCardBinding =
        ComponentIconButtonCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let { attributeSet ->
            val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.IconButtonView, 0, 0)
            val title = typedArray.getString(R.styleable.IconButtonView_title)
            val icon = typedArray.getResourceId(R.styleable.IconButtonView_icon, NO_ICON)
            val accessibilityMessage = typedArray.getString(R.styleable.IconButtonView_accessibilityMessage)

            setTitle(title)

            if (icon != NO_ICON) {
                setIcon(icon)
            }

            accessibilityMessage?.let {
                setAccessibilityMessage(it)
            }

            typedArray.recycle()
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.iconButton.text = title
    }

    fun setIcon(@DrawableRes icon: Int) {
        binding.iconButton.setIconResource(icon)
    }

    fun setOnClickListener(clickHandler: () -> Unit) {
        binding.iconButton.setOnClickListener {
            clickHandler.invoke()
        }
    }

    fun setAccessibilityMessage(accessibilityMessage: CharSequence) {
        binding.iconButton.setAccessibilityMessage(accessibilityMessage)
    }

    companion object {
        const val NO_ICON: Int = -1
    }
}
