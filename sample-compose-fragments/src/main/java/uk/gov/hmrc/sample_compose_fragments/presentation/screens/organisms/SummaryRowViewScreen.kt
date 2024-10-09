package uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowItem
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer
import uk.gov.hmrc.components.compose.organism.summaryrow.SummaryRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SeparatedViewContainerViewModel

object SummaryRowViewScreen {

    @Composable
    operator fun invoke() {
        ScreenScrollViewColumn {
            PlaceholderSlot {
                SummaryRowView(
                    titleText = stringResource(id = R.string.summary_row_placeholder_title),
                    rows = listOf {
                        MultiColumnRowView(
                            modifier = Modifier,
                            columnList = listOf(
                                MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_placeholder_text1)),
                                MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_placeholder_text2)),
                                MultiColumnRowItem(text = stringResource(id = R.string.multi_column_row_placeholder_text3))
                            )
                        )
                    }
                ) {
                    Log.d("Clicked", "Done")
                }
            }

            ExamplesSlot {
                HmrcCardView {
                    SeparatedViewContainer(
                        modifier = Modifier.padding(dimensions.hmrcSpacing16),
                        showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                        views = listOf(
                            {
                                SummaryRowView(
                                    modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16),
                                    titleText = stringResource(id = R.string.summary_row_example_1a_title),
                                    rows = listOf(

                                        {
                                            MultiColumnRowView(
                                                columnList = listOf(
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1a_row1_text1)),
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1a_row1_text2)),
                                                )
                                            )
                                        },
                                        {
                                            MultiColumnRowView(
                                                columnList = listOf(
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1a_row2_text1)),
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1a_row2_text2)),
                                                )
                                            )
                                        },
                                        {
                                            MultiColumnRowView(
                                                columnList = listOf(
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1a_row3_text1)),
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1a_row3_text2)),
                                                )
                                            )
                                        }


                                    )
                                ) {
                                    Log.d("Clicked", "Done")
                                }
                            },

                            {
                                SummaryRowView(
                                    modifier = Modifier.padding(top = dimensions.hmrcSpacing16),
                                    titleText = stringResource(id = R.string.summary_row_example_1b_title),
                                    rows = listOf(
                                        {
                                            MultiColumnRowView(
                                                columnList = listOf(
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1b_row1_text1)),
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1b_row1_text2)),
                                                )
                                            )
                                        },
                                        {
                                            MultiColumnRowView(
                                                columnList = listOf(
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1b_row2_text1)),
                                                    MultiColumnRowItem(text = stringResource(id = R.string.summary_row_example_1b_row2_text2)),
                                                )
                                            )
                                        }
                                    )
                                ) {
                                    Log.d("Clicked", "Done")
                                }

                            }
                        )
                    )
                }
            }
        }
    }

}