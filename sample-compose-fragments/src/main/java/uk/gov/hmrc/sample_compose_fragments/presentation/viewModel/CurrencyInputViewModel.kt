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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrencyInputViewModel @Inject constructor(): ViewModel() {

    private val _textInputErrorEmptyValidation = MutableStateFlow<String?>(null)
    val textInputErrorEmptyValidation: StateFlow<String?> get() = _textInputErrorEmptyValidation
    private val _textInputErrorEmptyValidation1 = MutableStateFlow<String?>(null)
    val textInputErrorEmptyValidation1: StateFlow<String?> get() = _textInputErrorEmptyValidation1
    private val _textInputErrorEmptyValidation2 = MutableStateFlow<String?>(null)
    val textInputErrorEmptyValidation2: StateFlow<String?> get() = _textInputErrorEmptyValidation2


    fun isEmptyValidation(input: String, errorText: String, id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                when (id) {
                    0 -> _textInputErrorEmptyValidation.value = if (input.isBlank()) errorText else null
                    1 -> _textInputErrorEmptyValidation1.value = if (input.isBlank()) errorText else null
                    2 -> _textInputErrorEmptyValidation2.value = if (input.isBlank()) errorText else null
                }
            }
        }
    }
}