/*
 * Copyright 2021 HM Revenue & Customs
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
package uk.gov.hmrc.components.organism.information

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.atom.button.SecondaryButton
import uk.gov.hmrc.components.databinding.ComponentInformationMessageCardBinding
import uk.gov.hmrc.components.extensions.setMargins
import uk.gov.hmrc.components.molecule.warning.WarningView.Companion.NO_ICON

class InformationMessageCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private val binding: ComponentInformationMessageCardBinding =
        ComponentInformationMessageCardBinding.inflate(LayoutInflater.from(context), this)

    var type: Type? = null
        set(value) {
            field = value
            type?.let {
                binding.warningView.setTextColor(it.headlineTint)
                binding.warningView.setIconTintColor(it.headlineTint)
                binding.body.setTextColor(ContextCompat.getColor(context, it.headlineTint))
                binding.rootLayout.setBackgroundColor(ContextCompat.getColor(context, it.headlineBackgroundColor))
                binding.rootLayout.visibility = View.VISIBLE
            }
        }

    init {
        attrs?.let { attributeSet ->
            val typedArray = context.theme
                .obtainStyledAttributes(attributeSet, R.styleable.InformationMessageCardView, 0, 0)
            setHeadline(typedArray.getString(R.styleable.InformationMessageCardView_headline))
            typedArray.getString(R.styleable.InformationMessageCardView_headlineContentDescription)?.let {
                setHeadlineContentDescription(it)
            }
            setHeadlineIcon(typedArray.getResourceId(R.styleable.InformationMessageCardView_headlineIcon, NO_ICON))
            setBody(typedArray.getString(R.styleable.InformationMessageCardView_body))
            val typeOrdinal = typedArray.getInt(R.styleable.InformationMessageCardView_type, -1)
            if (typeOrdinal != -1) {
                type = Type.values()[typeOrdinal]
            } else {
                binding.rootLayout.visibility = View.GONE
            }

            typedArray.recycle()
        }

        importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
    }

    fun setHeadline(headline: CharSequence?) {
        binding.warningView.setText(headline)
    }

    fun setHeadlineContentDescription(contentDesc: String) {
        binding.warningView.updateContentDescription(contentDesc)
    }

    fun setHeadlineIcon(resId: Int) {
        binding.warningView.setIcon(resId)
    }

    fun setBody(bodyText: String?) {
        if (bodyText.isNullOrBlank()) {
            binding.body.visibility = View.GONE
        } else {
            binding.body.visibility = View.VISIBLE
            binding.body.text = bodyText
        }
    }

    fun setHeadlineButtons(buttons: List<SecondaryButton>) {
        binding.headlineButtonsContainer.removeAllViews()

        val spacing8 = context.resources.getDimensionPixelSize(R.dimen.hmrc_spacing_8)
        val spacing16 = context.resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)

        binding.headlineButtonsContainer.visibility = if (buttons.isNotEmpty()) View.VISIBLE else View.GONE
        buttons.forEach {
            it.minHeight = 0
            it.setMargins(spacing16, spacing8, spacing16, 0)
            it.backgroundTintList = ContextCompat.getColorStateList(context, R.color.hmrc_white)
            it.gravity = Gravity.CENTER
            binding.headlineButtonsContainer.addView(it)
        }
    }

    enum class Type(val headlineBackgroundColor: Int, val headlineTint: Int) {
        WARNING(R.color.hmrc_information_message_warning_headline_background, R.color.hmrc_always_black),
        INFO(R.color.hmrc_information_message_info_headline_background, R.color.hmrc_white),
        URGENT(R.color.hmrc_information_message_urgent_headline_background, R.color.hmrc_white),
        NOTICE(R.color.hmrc_information_message_notice_headline_background, R.color.hmrc_white)
    }
}
