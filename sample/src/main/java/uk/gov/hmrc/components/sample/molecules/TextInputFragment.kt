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
import uk.gov.hmrc.components.molecule.input.TextInputView
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentTextInputBinding

class TextInputFragment : BaseComponentsFragment() {

    private var binding: FragmentTextInputBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.organisms_text_input, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTextInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners(binding.textInputExample1, getString(R.string.text_input_example_1_error))
        setListeners(binding.textInputExample2, getString(R.string.text_input_example_2_error))
        setListeners(binding.textInputExample3, getString(R.string.text_input_example_3_error))
    }

    private fun setListeners(textInput: TextInputView, errorText: String) {
        textInput.getEditText().addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateErrorOnTextChange(textInput, errorText)
            }
        })

        textInput.getEditText().setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                updateErrorOnTextChange(textInput, errorText)
            }
        }
    }

    private fun updateErrorOnTextChange(textInput: TextInputView, errorText: String) {
        if (textInput.getEditText().text.isNullOrBlank()) {
            textInput.setError(errorText)
        } else {
            textInput.setError("")
        }
    }
}
