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
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SelectRowViewModel

object SelectRowViewScreen {

    @Composable
    operator fun invoke(viewModel: SelectRowViewModel) {
        val exampleUiState by viewModel.exampleUiState.collectAsStateWithLifecycle()
        val placeholderUiState by viewModel.placeholderUiState.collectAsStateWithLifecycle()

        ScreenScrollViewColumn {
            //region Place Holder
            PlaceholderSlot {
                SelectRowView(
                    selectRowViewItems = placeholderUiState.items,
                    errorText = placeholderUiState.errorText,
                    selectedRowItem = placeholderUiState.selectedItem
                ) { selectedItem ->  // Handle the SelectRowView item click listener
                    viewModel.setPlaceholderSelectedRow(selectedItem)
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
                        viewModel.setExampleOneSelectedItem(selectedItem)
                    }
                }
                //endregion

                //region Example two
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = exampleUiState.exampleTwo.items,
                        selectedRowItem = exampleUiState.exampleTwo.selectedItem,
                        checkedIcon = R.drawable.components_select_row_tick_checked
                    ) { selectedItem -> viewModel.setExampleTwoSelectedItem(selectedItem) }
                }
                //endregion

                //region Example three with divider
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = exampleUiState.exampleThree.items,
                        selectedRowItem = exampleUiState.exampleThree.selectedItem,
                        showDivider = true
                    ) { selectedItem -> viewModel.setExampleThreeSelectedItem(selectedItem) }
                }
                //endregion
            }
        }
    }
}
