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
package uk.gov.hmrc.components.sample.molecules

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.gov.hmrc.components.molecule.input.CurrencyInputView
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentCurrencyInputBinding

class CurrencyInputFragment : BaseComponentsFragment() {

    private var binding: FragmentCurrencyInputBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.organisms_currency_input, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCurrencyInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners(binding.currencyInputExample1, getString(R.string.currency_input_example_1_error))
        setListeners(binding.currencyInputExample2, getString(R.string.currency_input_example_2_error))
        setListeners(binding.currencyInputExample3, getString(R.string.currency_input_example_3_error))

        // To highlight any regressions regarding a bug where setting a right drawable (clear icon)
        // would not retain the left drawable, thus losing it when some text had changed via setText()
        binding.currencyInputExample3.getEditText().setText(getString(R.string.currency_input_example_3_text))
    }

    private fun setListeners(currencyInput: CurrencyInputView, errorText: String) {
        currencyInput.getEditText().addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateErrorOnTextChange(currencyInput, errorText)
            }
        })

        currencyInput.getEditText().setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                updateErrorOnTextChange(currencyInput, errorText)
            }
        }
    }

    private fun updateErrorOnTextChange(currencyInput: CurrencyInputView, errorText: String) {
        if (currencyInput.getEditText().text.isNullOrBlank()) {
            currencyInput.setError(errorText)
        } else {
            currencyInput.setError("")
        }
    }
}
