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
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowItem
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowView
import uk.gov.hmrc.components.compose.ui.extensions.enableTalkBackMergeAccessibility
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcCard
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
                        MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_placeholder_text1)),
                        MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_placeholder_text2)),
                        MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_placeholder_text3))
                    )
                )
            }

            ExamplesSlot {

                // Simple MultiColumnRowView ColumnCount = 1
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_1))
                        )
                    )
                }

                // Error text in single column row view ColumnCount = 1
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.longest_text),
                                textStyle = { HmrcTheme.typography.errorText }
                            )
                        )
                    )
                }

                // Estimated Income text MultiColumnRowView ColumnCount = 2
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier
                            .enableTalkBackMergeAccessibility()
                            .padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_1)),
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_2))
                        )
                    )
                }

                // MultiColumnRowView bold text columnCount = 2
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.longer_text),
                                textStyle = { HmrcTheme.typography.h6 }
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                                textStyle = { HmrcTheme.typography.h6 }
                            )
                        )
                    )
                }

                // MultiColumnRowView Account and number Body and bold text columnCount = 2
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_6)),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_7),
                                textStyle = { HmrcTheme.typography.h6 }
                            )
                        )
                    )
                }

                // MultiColumnRowView Heading and  text columnCount = 2
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_8),
                                textStyle = { HmrcTheme.typography.h5 }
                            ),
                            MultiColumnRowItem(text = stringResource(id = R.string.summary_row_placeholder_row1_text2))
                        )
                    )
                }

                // MultiColumnRowView text columnCount = 3
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_3)),
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_4)),
                            MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_example_text_5))
                        )
                    )
                }

                // MultiColumnRowView long text columnCount = 3
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.long_text),
                            )
                        )
                    )
                }

                // MultiColumnRowView body text same weight column = 2
                HmrcCard(
                    modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                ) {
                    MultiColumnRowView(
                        modifier = Modifier
                            .enableTalkBackMergeAccessibility()
                            .padding(HmrcTheme.dimensions.hmrcSpacing16),
                        columnList = listOf(
                            MultiColumnRowItem(text = stringResource(id = R.string.summary_row_placeholder_row1_text1)),
                            MultiColumnRowItem(text = stringResource(id = R.string.summary_row_placeholder_row1_text2))
                        )
                    )
                }
            }
        }
    }
}

