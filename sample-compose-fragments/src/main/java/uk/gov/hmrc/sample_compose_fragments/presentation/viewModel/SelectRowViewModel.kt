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
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView.NO_ROW_SELECTED_POSITION
import javax.inject.Inject

@HiltViewModel
class SelectRowViewModel @Inject constructor() : ViewModel() {

    // region Place Holder UI State
    private val errorText = MutableStateFlow("")
    private val rowSelectedPosition = MutableStateFlow(NO_ROW_SELECTED_POSITION)
    val placeHolderUiState: StateFlow<PlaceHolderState> = combine(
        errorText,
        rowSelectedPosition
    ) { errorText, rowSelectedPosition ->
        PlaceHolderState(
            errorText = errorText,
            rowSelectedPosition = rowSelectedPosition
        )
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = PlaceHolderState()
    )

    fun setPlaceHolderRowSelectedPosition(value: Int) = viewModelScope.launch { rowSelectedPosition.emit(value) }
    fun setPlaceHolderErrorText(value: String) = viewModelScope.launch { errorText.emit(value) }
    //endregion

    // region Example UI State
    private val rowSelectedPositionExample1 = MutableStateFlow(FIRST_ROW_SELECTED_POSITION)
    private val rowSelectedPositionExample2 = MutableStateFlow(FIRST_ROW_SELECTED_POSITION)
    private val errorTextExample1 = MutableStateFlow("")

    val exampleUiState: StateFlow<ExampleUiState> = combine(
        rowSelectedPositionExample1,
        rowSelectedPositionExample2,
        errorTextExample1,
    ) { rowSelectedPositionExample1, rowSelectedPositionExample2, errorTextExample1 ->
        ExampleUiState(
            rowSelectedPositionExample1 = rowSelectedPositionExample1,
            rowSelectedPositionExample2 = rowSelectedPositionExample2,
            errorTextExample1 = errorTextExample1,
        )
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = ExampleUiState()
    )

    fun setRowSelectedPositionExample1(value: Int) = viewModelScope.launch { rowSelectedPositionExample1.emit(value) }

    fun setRowSelectedPositionExample2(value: Int) = viewModelScope.launch { rowSelectedPositionExample2.emit(value) }

    fun setErrorTextExample1(value: String) = viewModelScope.launch { errorTextExample1.emit(value) }
    //endregion

    data class ExampleUiState(
        val rowSelectedPositionExample1: Int = FIRST_ROW_SELECTED_POSITION,
        val rowSelectedPositionExample2: Int = FIRST_ROW_SELECTED_POSITION,
        val errorTextExample1: String = ""
    )

    data class PlaceHolderState(
        val errorText: String = "",
        val rowSelectedPosition: Int = NO_ROW_SELECTED_POSITION,
    )

    companion object {
        const val FIRST_ROW_SELECTED_POSITION = 0
    }
}



