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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowItem
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object MultiColumnRowViewScreen {

    @Composable
    operator fun invoke() {
        ScreenScrollViewColumn {
            PlaceholderSlot {
                MultiColumnRowView(
                    modifier = Modifier,
                    columnList = listOf(
                        MultiColumnRowItem(
                            text = stringResource(id = R.string.multi_column_row_placeholder_text1),
                            modifier = Modifier.weight(1f),
                            textStyle = HmrcTheme.typography.body
                        ),
                        MultiColumnRowItem(
                            text = stringResource(id = R.string.multi_column_row_placeholder_text2),
                            modifier = Modifier.weight(0.5f),
                            textStyle = HmrcTheme.typography.body
                        ),
                        MultiColumnRowItem(
                            text = stringResource(id = R.string.multi_column_row_placeholder_text3),
                            modifier = Modifier.weight(0.5f),
                            textStyle = HmrcTheme.typography.body.copy(textAlign = TextAlign.Right),
                        )
                    )
                )
            }

            ExamplesSlot {

                // Simple MultiColumnRowView ColumnCount = 1
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16), columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_1),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            )
                        )
                    )
                }

                // Error text in single column row view ColumnCount = 1
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.longest_text),
                                textStyle = HmrcTheme.typography.errorText,
                                modifier = Modifier.weight(1f)
                            )
                        )
                    )
                }

                // Estimated Income text MultiColumnRowView ColumnCount = 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_1),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_2),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier
                            )
                        )
                    )
                }

                // MultiColumnRowView bold text columnCount = 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.longer_text),
                                textStyle = HmrcTheme.typography.h6,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                                textStyle = HmrcTheme.typography.h6.copy(textAlign = TextAlign.Right),
                                modifier = Modifier.weight(0.5f)
                            )
                        )
                    )
                }

                // MultiColumnRowView Account and number Body and bold text columnCount = 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_6),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_7),
                                textStyle = HmrcTheme.typography.h6.copy(textAlign = TextAlign.Right),
                                modifier = Modifier.weight(0.5f)
                            )
                        )
                    )
                }

                // MultiColumnRowView Heading and  text columnCount = 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_8),
                                textStyle = HmrcTheme.typography.h5,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.summary_row_placeholder_row1_text2),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier
                            )
                        )
                    )
                }

                // MultiColumnRowView text columnCount = 3
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_3),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_4),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(0.5f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_5),
                                textStyle = HmrcTheme.typography.body.copy(textAlign = TextAlign.Right),
                                modifier = Modifier.weight(0.5f)
                            )
                        )
                    )
                }

                // MultiColumnRowView long text columnCount = 3
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                                textStyle = HmrcTheme.typography.body.copy(textAlign = TextAlign.Right),
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                                textStyle = HmrcTheme.typography.body.copy(textAlign = TextAlign.Right),
                                modifier = Modifier.weight(1f)
                            )
                        )
                    )
                }

                // MultiColumnRowView body text same weight column = 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.summary_row_placeholder_row1_text1),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.summary_row_placeholder_row1_text2),
                                textStyle = HmrcTheme.typography.body.copy(textAlign = TextAlign.Right),
                                modifier = Modifier.weight(1f)
                            )
                        )
                    )
                }
            }
        }
    }
}

