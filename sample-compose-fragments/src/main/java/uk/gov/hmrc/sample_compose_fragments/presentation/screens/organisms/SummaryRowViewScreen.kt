package uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowItem
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer
import uk.gov.hmrc.components.compose.organism.summaryrow.ReaderTrait
import uk.gov.hmrc.components.compose.organism.summaryrow.SummaryRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun SummaryRowViewScreen(onClickAction: () -> Unit) {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            SummaryRowView(
                modifier = Modifier,
                titleText = stringResource(id = R.string.summary_row_placeholder_title),
                rows = listOf {
                    MultiColumnRowView(
                        modifier = Modifier,
                        columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_placeholder_text1)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_placeholder_text2)
                            ),
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_placeholder_text3)
                            )
                        )
                    )
                }
            ) {
                onClickAction()
            }
        }

        ExamplesSlot {
            HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                SeparatedViewContainer(
                    showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                    dividerHorizontalPadding = dimensions.hmrcSpacing16,
                    views = listOf(
                        {
                            // Example : 1
                            SummaryRowView(
                                titleText = stringResource(id = R.string.summary_row_example_1a_title),
                                isBoldTitleTextAppearance = false,
                                chevronContentDescription = stringResource(
                                    id = R.string.summary_row_example_1a_accessibility_message
                                ),
                                rows = listOf(

                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1a_row1_text1
                                                    )
                                                ),
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1a_row1_text2
                                                    )
                                                ),
                                            )
                                        )
                                    },
                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1a_row2_text1
                                                    )
                                                ),
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1a_row2_text2
                                                    )
                                                ),
                                            )
                                        )
                                    },
                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1a_row3_text1
                                                    )
                                                ),
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1a_row3_text2
                                                    )
                                                ),
                                            )
                                        )
                                    }


                                )
                            ) {
                                onClickAction()
                            }
                        },

                        {
                            // Example : 2
                            SummaryRowView(
                                titleText = stringResource(id = R.string.summary_row_example_1b_title),
                                rows = listOf(
                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1b_row1_text1
                                                    )
                                                ),
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1b_row1_text2
                                                    )
                                                ),
                                            )
                                        )
                                    },
                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1b_row2_text1
                                                    )
                                                ),
                                                MultiColumnRowItem(
                                                    text = stringResource(
                                                        id = R.string.summary_row_example_1b_row2_text2
                                                    )
                                                ),
                                            )
                                        )
                                    }
                                )
                            ) {
                                onClickAction()
                            }

                        }
                    )
                )
            }

            // Example : 3
            HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                SummaryRowView(
                    titleText = stringResource(id = R.string.summary_row_example_2_title),
                    readerTrait = ReaderTrait.READER_TRAIT_SIMPLE,
                    rows = listOf {
                        MultiColumnRowView(
                            columnList = listOf(
                                MultiColumnRowItem(
                                    text = stringResource(id = R.string.summary_row_example_2_row1_text1)
                                )
                            )
                        )
                    }
                ) {
                    onClickAction()
                }
            }

            HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                SeparatedViewContainer(
                    showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                    dividerHorizontalPadding = dimensions.hmrcSpacing16,
                    views = listOf(
                        {
                            // Example : 4
                            SummaryRowView(
                                titleText = stringResource(id = R.string.longer_text),
                                rows = listOf {
                                    MultiColumnRowView(
                                        columnList = listOf(
                                            MultiColumnRowItem(text = stringResource(id = R.string.longest_text)),
                                        )
                                    )
                                }
                            ) {
                                onClickAction()
                            }
                        },

                        {
                            // Example : 5
                            SummaryRowView(
                                titleText = stringResource(id = R.string.longer_text),
                                rows = listOf {
                                    MultiColumnRowView(
                                        columnList = listOf(
                                            MultiColumnRowItem(text = stringResource(id = R.string.long_text)),
                                            MultiColumnRowItem(text = stringResource(id = R.string.long_text)),
                                        )
                                    )
                                }
                            )
                        },
                        {
                            // Example : 6
                            SummaryRowView(
                                titleText = stringResource(id = R.string.longer_text),
                                titleMaxLines = 2,
                                rows = listOf(
                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(text = stringResource(id = R.string.long_text)),
                                                MultiColumnRowItem(text = stringResource(id = R.string.long_text)),
                                                MultiColumnRowItem(text = stringResource(id = R.string.long_text)),
                                            )
                                        )
                                    },
                                    {
                                        MultiColumnRowView(
                                            columnList = listOf(
                                                MultiColumnRowItem(text = stringResource(id = R.string.long_text))
                                            )
                                        )
                                    }
                                )
                            ) {
                                onClickAction()
                            }
                        }
                    )
                )
            }

            // Example : 7
            HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                SummaryRowView(
                    titleText = stringResource(id = R.string.summary_row_example_2_title),
                    icon = painterResource(id = R.drawable.ic_calendar),
                    readerTrait = ReaderTrait.READER_TRAIT_SIMPLE,
                    rows = listOf {
                        MultiColumnRowView(
                            columnList = listOf(
                                MultiColumnRowItem(text = stringResource(id = R.string.long_text))
                            )
                        )
                    }
                ) {
                    onClickAction()
                }
            }
        }
    }
}
