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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView.SelectRowViewItem
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HMRCPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SelectRowViewModel
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SelectRowViewModel.ExampleUiState
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SelectRowViewModel.SelectRowViewState

object SelectRowViewScreen {

    @Composable
    operator fun invoke(viewModel: SelectRowViewModel) {
        val exampleUiState by viewModel.exampleUiState.collectAsStateWithLifecycle()
        val placeholderUiState by viewModel.placeholderUiState.collectAsStateWithLifecycle()

        SelectRowViewContainer(
            exampleUiState, placeholderUiState,
            setPlaceholderSelectedRow = viewModel::setPlaceholderSelectedRow,
            setExampleOneSelectedItem = viewModel::setExampleOneSelectedItem,
            setExampleTwoSelectedItem = viewModel::setExampleTwoSelectedItem,
            setExampleThreeSelectedItem = viewModel::setExampleThreeSelectedItem,
        )
    }

    @Composable
    fun SelectRowViewContainer(
        exampleUiState: ExampleUiState,
        placeholderUiState: SelectRowViewState,
        setPlaceholderSelectedRow: (SelectRowViewItem) -> Unit,
        setExampleOneSelectedItem: (SelectRowViewItem) -> Unit,
        setExampleTwoSelectedItem: (SelectRowViewItem) -> Unit,
        setExampleThreeSelectedItem: (SelectRowViewItem) -> Unit,
    ) {
        ScreenScrollViewColumn {
            //region Place Holder
            PlaceholderSlot {
                SelectRowView(
                    selectRowViewItems = placeholderUiState.items,
                    errorText = placeholderUiState.errorText,
                    selectedRowItem = placeholderUiState.selectedItem
                ) { selectedItem ->  // Handle the SelectRowView item click listener
                    setPlaceholderSelectedRow(selectedItem)
                }
            }
            //endregion

            ExamplesSlot {
                //region Example one
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = exampleUiState.exampleOne.items,
                        errorText = exampleUiState.exampleOne.errorText,
                        selectedRowItem = exampleUiState.exampleOne.selectedItem
                    ) { selectedItem ->
                        setExampleOneSelectedItem(selectedItem)
                    }
                }
                //endregion

                //region Example two
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = exampleUiState.exampleTwo.items,
                        selectedRowItem = exampleUiState.exampleTwo.selectedItem,
                        checkedIcon = R.drawable.components_select_row_tick_checked
                    ) { selectedItem -> setExampleTwoSelectedItem(selectedItem) }
                }
                //endregion

                //region Example three with divider
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = exampleUiState.exampleThree.items,
                        selectedRowItem = exampleUiState.exampleThree.selectedItem,
                        showDivider = true
                    ) { selectedItem -> setExampleThreeSelectedItem(selectedItem) }
                }
                //endregion
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun SelectRowViewScreenPreview() {
    HMRCPreview {
        val placeholderState = SelectRowViewState(
            listOf(SelectRowViewItem(R.string.select_row_body_description)),
            errorText = R.string.select_row_error_message
        )

        val exampleOneItemOne = SelectRowViewItem(R.string.select_row_view_first_row)
        val exampleOneItemTwo = SelectRowViewItem(R.string.select_row_view_second_row)
        val exampleOneItemThree = SelectRowViewItem(R.string.select_row_view_error_row)
        val exampleOneItemFour = SelectRowViewItem(R.string.longer_text)
        val exampleOneItems = listOf(exampleOneItemOne, exampleOneItemTwo, exampleOneItemThree, exampleOneItemFour)
        val exampleOne = SelectRowViewState(exampleOneItems, exampleOneItemOne)

        val exampleTwoItemOne = SelectRowViewItem(R.string.select_row_view_first_row)
        val exampleTwoItemTwo = SelectRowViewItem(R.string.select_row_view_second_row)
        val exampleTwoItemThree = SelectRowViewItem(R.string.select_row_view_third_row)
        val exampleTwoItemFour = SelectRowViewItem(R.string.select_row_view_fourth_row)
        val exampleTwoItems = listOf(exampleTwoItemOne, exampleTwoItemTwo, exampleTwoItemThree, exampleTwoItemFour)
        val exampleTwo = SelectRowViewState(exampleTwoItems, exampleTwoItemTwo)

        val exampleThreeItemOne = SelectRowViewItem(R.string.select_row_view_first_row)
        val exampleThreeItemTwo = SelectRowViewItem(R.string.select_row_view_second_row)
        val exampleThreeItemThree = SelectRowViewItem(R.string.select_row_view_third_row)
        val exampleThreeItemFour = SelectRowViewItem(R.string.select_row_view_fourth_row)
        val exampleThreeItems =
            listOf(exampleThreeItemOne, exampleThreeItemTwo, exampleThreeItemThree, exampleThreeItemFour)
        val exampleThree = SelectRowViewState(exampleThreeItems, exampleThreeItemOne)

        SelectRowViewScreen.SelectRowViewContainer(
            exampleUiState = ExampleUiState(exampleOne, exampleTwo, exampleThree),
            placeholderUiState = placeholderState,
            setPlaceholderSelectedRow = {},
            setExampleOneSelectedItem = {},
            setExampleTwoSelectedItem = {},
            setExampleThreeSelectedItem = {}

        )
    }
}
