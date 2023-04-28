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
package uk.gov.hmrc.sample_compose_fragments.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TextInputViewModel: ViewModel() {

    private val _textInputError = MutableStateFlow<String?>(null)
    val textInputError: StateFlow<String?> get() = _textInputError

    fun validateInput(input: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _textInputError.value = if (input == ERROR_INPUT_PHRASE) "Error: this is an error" else null
            }
        }
    }

    fun isEmptyValidation(text: String): Boolean {
        return text.isBlank()
    }

    fun validateCharCount(characterCount: Int, text: String): Boolean {
        return text.length > characterCount
    }

    companion object {
        private const val ERROR_INPUT_PHRASE = "error"
    }


}