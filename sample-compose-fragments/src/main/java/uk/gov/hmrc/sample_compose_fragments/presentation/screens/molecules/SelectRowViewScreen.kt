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

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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

    private const val ERROR_ITEM_INDEX = 2

    @Composable
    operator fun invoke(viewModel: SelectRowViewModel) {
        val context = LocalContext.current

        val exampleUiState by viewModel.exampleUiState.collectAsStateWithLifecycle()
        val placeHolderUiState by viewModel.placeHolderUiState.collectAsStateWithLifecycle()

        ScreenScrollViewColumn {
            //region Place Holder
            PlaceholderSlot {
                viewModel.setPlaceHolderErrorText(stringResource(id = R.string.select_row_error_message))
                SelectRowView(
                    selectRowViewItems = listOf(stringResource(R.string.select_row_body_description)),
                    errorText = placeHolderUiState.errorText,
                    rowSelectedPosition = placeHolderUiState.rowSelectedPosition
                ) { position, value ->  // Handle the SelectRowView item click listener
                    viewModel.setPlaceHolderRowSelectedPosition(position)
                }
            }
            //endregion

            ExamplesSlot {
                //region Example one
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = listOf(
                            stringResource(R.string.select_row_view_first_row),
                            stringResource(R.string.select_row_view_second_row),
                            stringResource(R.string.select_row_view_error_row),
                            stringResource(id = R.string.longer_text)
                        ),
                        errorText = exampleUiState.errorTextExample1,
                        rowSelectedPosition = exampleUiState.rowSelectedPositionExample1
                    ) { position, value -> // Handle the SelectRowView item click listener
                        viewModel.setRowSelectedPositionExample1(position)
                        if (position == ERROR_ITEM_INDEX) {
                            viewModel.setErrorTextExample1(context.getString(R.string.select_row_error_message))
                        } else {
                            viewModel.setErrorTextExample1("")
                        }
                        Toast.makeText(context, "$value selected", Toast.LENGTH_SHORT).show()
                    }
                }
                //endregion

                //region Example two
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = listOf(
                            stringResource(R.string.select_row_view_first_row),
                            stringResource(R.string.select_row_view_second_row),
                            stringResource(R.string.select_row_view_third_row),
                            stringResource(R.string.select_row_view_fourth_row)
                        ),
                        rowSelectedPosition = exampleUiState.rowSelectedPositionExample2,
                        checkedIcon = R.drawable.components_select_row_tick_checked
                    ) { position, value -> // Handle the SelectRowView item click listener
                        viewModel.setRowSelectedPositionExample2(position)
                    }
                }
                //endregion

                //region Example three with divider
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = listOf(
                            stringResource(R.string.select_row_view_first_row),
                            stringResource(R.string.select_row_view_second_row),
                            stringResource(R.string.select_row_view_third_row),
                            stringResource(R.string.select_row_view_fourth_row)
                        ),
                        rowSelectedPosition = exampleUiState.rowSelectedPositionExample3,
                        showDivider = true
                    ) { position, value -> // Handle the SelectRowView item click listener
                        viewModel.setRowSelectedPositionExample3(position)
                    }
                }
                //endregion
            }
        }
    }
}
