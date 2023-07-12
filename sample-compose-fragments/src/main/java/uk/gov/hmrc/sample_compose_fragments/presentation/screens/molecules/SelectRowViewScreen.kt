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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcCard
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object SelectRowViewScreen {

    private const val THIRD_ROW_VIEW = 3

    @Composable
    operator fun invoke() {
        val context = LocalContext.current
        ScreenScrollViewColumn {
            PlaceholderSlot {
                SelectRowView(
                    selectRowViewItems = listOf("(Body) Description"),
                    errorText = "This is an error"
                ) { position, value ->
                    // Handle the SelectRowView item click listener
                }
            }

            ExamplesSlot {
                //region Example one
                HmrcCard(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    val errorText = remember { mutableStateOf("") }
                    SelectRowView(
                        selectRowViewItems = listOf(
                            "First row",
                            "Second row",
                            "Show an error",
                            stringResource(id = R.string.longer_text)
                        ),
                        errorText = errorText.value,
                        initialSelection = 0
                    ) { position, value ->
                        if (position == THIRD_ROW_VIEW - 1) {
                            errorText.value = "This is an error"
                        }
                        Toast.makeText(
                            context,
                            "Position: $position selected\nValue: $value",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                //endregion

                //region Example two
                HmrcCard(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = listOf(
                            "First row",
                            "Second row",
                            "Third row",
                            "Fourth row"
                        ),
                        initialSelection = 0,
                        checkedIcon = R.drawable.components_select_row_tick_checked
                    ) { position, value ->
                        // Handle the SelectRowView item click listener
                    }
                }
                //endregion
            }
        }
    }
}
