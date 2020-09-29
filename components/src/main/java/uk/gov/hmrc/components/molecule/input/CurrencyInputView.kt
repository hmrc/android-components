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
package uk.gov.hmrc.components.molecule.input

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import uk.gov.hmrc.components.R

class CurrencyInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : TextInputView(context, attrs, defStyle, defStyleRes) {

    init {
        var decimalsEnabled = true
        attrs?.let { it ->
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.CurrencyInputView, 0, 0)
            decimalsEnabled = typedArray.getBoolean(R.styleable.CurrencyInputView_decimalsEnabled, true)
            typedArray.recycle()
        }

        getEditText().inputType = InputType.TYPE_CLASS_NUMBER
        if (decimalsEnabled) {
            getEditText().inputType = getEditText().inputType or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        setPrefixText(context.getString(R.string.currency_input_prefix))
        setCounterEnabled(false)
    }
}
