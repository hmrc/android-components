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
package uk.gov.hmrc.components.molecule.inset

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentInsetTextBinding

class InsetTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : InsetView(context, attrs, defStyle, defStyleRes) {

    private val binding: ComponentInsetTextBinding =
            ComponentInsetTextBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.InsetTextView, 0, 0)
            val insetText = typedArray.getString(R.styleable.InsetTextView_text)

            setText(insetText)

            typedArray.recycle()
        }
    }

    fun setText(text: CharSequence?) {
        binding.message.text = text
    }
}
