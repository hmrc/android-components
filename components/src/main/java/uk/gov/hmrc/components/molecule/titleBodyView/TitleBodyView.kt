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
package uk.gov.hmrc.components.molecule.titleBodyView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentTitleBodyBinding

abstract class TitleBodyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val binding: ComponentTitleBodyBinding =
        ComponentTitleBodyBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.TitleBodyView, 0, 0)
            val title = typedArray.getString(R.styleable.TitleBodyView_title)
            val body = typedArray.getString(R.styleable.TitleBodyView_body)

            setTitle(title)
            setBody(body)

            typedArray.recycle()
        }

        orientation = VERTICAL
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    fun setBody(body: CharSequence?) {
        binding.body.text = body
    }
}
