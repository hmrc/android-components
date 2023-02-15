/*
 * Copyright 2023 HM Revenue & Customs
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
import com.google.android.material.card.MaterialCardView
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentMiniAdvertCardBinding
import uk.gov.hmrc.components.extensions.setAccessibilityMessage

class MiniAdvertCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private val binding: ComponentMiniAdvertCardBinding =
        ComponentMiniAdvertCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let { attributeSet ->
            val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.MiniAdvertButtonView, 0, 0)
            val title = typedArray.getString(R.styleable.MiniAdvertButtonView_title)
            val accessibilityMessage = typedArray.getString(R.styleable.MiniAdvertButtonView_accessibilityMessage)

            setTitle(title)

            accessibilityMessage?.let { setAccessibilityMessage(it) }

            typedArray.recycle()
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.miniAdvertButton.text = title
    }

    fun setOnClickListener(clickHandler: () -> Unit) {
        binding.miniAdvertButton.setOnClickListener { clickHandler.invoke() }
    }

    fun setAccessibilityMessage(accessibilityMessage: CharSequence) {
        binding.miniAdvertButton.setAccessibilityMessage(accessibilityMessage)
    }
}
