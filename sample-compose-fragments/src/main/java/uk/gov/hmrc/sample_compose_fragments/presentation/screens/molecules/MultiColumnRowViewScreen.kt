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
import uk.gov.hmrc.components.compose.molecule.titleBody.H4TitleBodyView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
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
                            textStyle = HmrcTheme.typography.body
                        )
                    )
                )
            }

            ExamplesSlot {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = hmrc_spacing_16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(hmrc_spacing_16), columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.multi_column_row_example_text_1),
                                textStyle = HmrcTheme.typography.body,
                                modifier = Modifier.weight(1f)
                            )
                        )
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = hmrc_spacing_16),
                    shape = RoundedCornerShape(0),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
                ) {
                    MultiColumnRowView(
                        modifier = Modifier.padding(hmrc_spacing_16), columnList = listOf(
                            MultiColumnRowItem(
                                text = stringResource(id = R.string.longest_text),
                                textStyle = HmrcTheme.typography.errorText,
                                modifier = Modifier.weight(1f)
                            )
                        )
                    )
                }

            }
        }
    }

}