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
package uk.gov.hmrc.components.atom.bullet

import android.content.Context
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.util.AttributeSet
import androidx.core.text.getSpans
import androidx.core.text.toSpannable
import com.google.android.material.textview.MaterialTextView
import uk.gov.hmrc.components.R

class BulletedTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialTextView(context, attrs) {

    override fun setText(text: CharSequence?, type: BufferType?) {
        val newText = text ?: ""
        if (newText.toSpannable().getSpans<BulletSpan>().isEmpty()) {
            val gapWidth = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
            val bulletSpan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                BulletSpan(gapWidth, currentTextColor, resources.getDimensionPixelSize(R.dimen.text_bullet_point))
            } else {
                BulletSpan(gapWidth)
            }
            val spannable = SpannableString(newText).apply {
                setSpan(bulletSpan, 0, newText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            super.setText(spannable, type)
        } else {
            super.setText(newText, type)
        }
    }
}
