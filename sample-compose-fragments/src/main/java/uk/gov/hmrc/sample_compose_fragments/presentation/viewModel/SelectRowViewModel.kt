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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectRowViewModel @Inject constructor() : ViewModel() {

    // region Place Holder UI State
    private val errorText = MutableStateFlow("")
    private val initialSelection = MutableStateFlow(-1)
    val placeHolderUiState: StateFlow<PlaceHolderState> = combine(
        errorText,
        initialSelection
    ) { errorText, initialSelection ->
        PlaceHolderState(
            errorText = errorText,
            initialSelection = initialSelection
        )
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = PlaceHolderState()
    )

    fun setPlaceHolderInitialRowSelection(value: Int) = viewModelScope.launch { initialSelection.emit(value) }
    fun setPlaceHolderErrorText(value: String) = viewModelScope.launch { errorText.emit(value) }
    //endregion

    // region Example UI State
    private val initialRowSelectionExample1 = MutableStateFlow(0)
    private val initialRowSelectionExample2 = MutableStateFlow(0)
    private val errorTextExample1 = MutableStateFlow("")

    val exampleUiState: StateFlow<ExampleUiState> = combine(
        initialRowSelectionExample1,
        initialRowSelectionExample2,
        errorTextExample1,
    ) { initialRowSelectionExample1, initialRowSelectionExample2, errorTextExample1 ->
        ExampleUiState(
            initialRowSelectionExample1 = initialRowSelectionExample1,
            initialRowSelectionExample2 = initialRowSelectionExample2,
            errorTextExample1 = errorTextExample1,
        )
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = ExampleUiState()
    )

    fun setInitialRowSelectionExample1(value: Int) = viewModelScope.launch { initialRowSelectionExample1.emit(value) }

    fun setInitialRowSelectionExample2(value: Int) = viewModelScope.launch { initialRowSelectionExample2.emit(value) }

    fun setErrorTextExample1(value: String) = viewModelScope.launch { errorTextExample1.emit(value) }
    //endregion
}

data class ExampleUiState(
    val initialRowSelectionExample1: Int = 0,
    val initialRowSelectionExample2: Int = 0,
    val errorTextExample1: String = ""
)

data class PlaceHolderState(
    val errorText: String = "",
    val initialSelection: Int = -1,
)

