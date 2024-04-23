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

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView.SelectRowViewItem
import uk.gov.hmrc.sample_compose_components.R
import javax.inject.Inject

@HiltViewModel
class SelectRowViewModel @Inject constructor() : ViewModel() {

    private val placeholderState = SelectRowViewState(
        listOf(SelectRowViewItem(R.string.select_row_body_description)),
        errorText = R.string.select_row_error_message
    )

    private val exampleOneItemOne = SelectRowViewItem(R.string.select_row_view_first_row)
    private val exampleOneItemTwo = SelectRowViewItem(R.string.select_row_view_second_row)
    private val exampleOneItemThree = SelectRowViewItem(R.string.select_row_view_error_row)
    private val exampleOneItemFour = SelectRowViewItem(R.string.longer_text)
    private val exampleOneErrorText = R.string.select_row_error_message
    private val exampleOneItems = listOf(exampleOneItemOne, exampleOneItemTwo, exampleOneItemThree, exampleOneItemFour)
    private var exampleOne = SelectRowViewState(exampleOneItems, exampleOneItemOne)

    private val exampleTwoItemOne = SelectRowViewItem(R.string.select_row_view_first_row)
    private val exampleTwoItemTwo = SelectRowViewItem(R.string.select_row_view_second_row)
    private val exampleTwoItemThree = SelectRowViewItem(R.string.select_row_view_third_row)
    private val exampleTwoItemFour = SelectRowViewItem(R.string.select_row_view_fourth_row)
    private val exampleTwoItems = listOf(exampleTwoItemOne, exampleTwoItemTwo, exampleTwoItemThree, exampleTwoItemFour)
    private var exampleTwo = SelectRowViewState(exampleTwoItems, exampleTwoItemTwo)

    private val exampleThreeItemOne = SelectRowViewItem(R.string.select_row_view_first_row)
    private val exampleThreeItemTwo = SelectRowViewItem(R.string.select_row_view_second_row)
    private val exampleThreeItemThree = SelectRowViewItem(R.string.select_row_view_third_row)
    private val exampleThreeItemFour = SelectRowViewItem(R.string.select_row_view_fourth_row)
    private val exampleThreeItems =
        listOf(exampleThreeItemOne, exampleThreeItemTwo, exampleThreeItemThree, exampleThreeItemFour)
    private var exampleThree = SelectRowViewState(exampleThreeItems, exampleThreeItemOne)

    // region Place Holder UI State
    private val _placeholderUiState = MutableStateFlow(placeholderState)
    val placeholderUiState: StateFlow<SelectRowViewState> get() = _placeholderUiState

    fun setPlaceholderSelectedRow(item: SelectRowViewItem) = viewModelScope.launch {
        val updatedItem = placeholderState.copy(selectedItem = item)
        _placeholderUiState.emit(updatedItem)
    }
    //endregion

    // region Example UI State
    private val exampleOneState = MutableStateFlow(exampleOne)
    private val exampleTwoState = MutableStateFlow(exampleTwo)
    private val exampleThreeState = MutableStateFlow(exampleThree)

    val exampleUiState: StateFlow<ExampleUiState> = combine(
        exampleOneState,
        exampleTwoState,
        exampleThreeState,
    ) { exampleOne, exampleTwo, exampleThree ->
        ExampleUiState(
            exampleOne = exampleOne,
            exampleTwo = exampleTwo,
            exampleThree = exampleThree
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ExampleUiState(exampleOne, exampleTwo, exampleThree)
    )

    fun setExampleOneSelectedItem(item: SelectRowViewItem) = viewModelScope.launch {
        val updatedItem = exampleOne.copy(
            selectedItem = item,
            errorText = if (item == exampleOneItemThree) exampleOneErrorText else null
        )
        exampleOneState.emit(updatedItem)
    }

    fun setExampleTwoSelectedItem(item: SelectRowViewItem) = viewModelScope.launch {
        val updatedItem = exampleTwo.copy(selectedItem = item)
        exampleTwoState.emit(updatedItem)
    }

    fun setExampleThreeSelectedItem(item: SelectRowViewItem) = viewModelScope.launch {
        val updatedItem = exampleThree.copy(selectedItem = item)
        exampleThreeState.emit(updatedItem)
    }

    //endregion

    data class ExampleUiState(
        val exampleOne: SelectRowViewState,
        val exampleTwo: SelectRowViewState,
        val exampleThree: SelectRowViewState,
    )

    data class SelectRowViewState(
        val items: List<SelectRowViewItem>,
        var selectedItem: SelectRowViewItem? = null,
        @StringRes var errorText: Int? = null
    )
}



