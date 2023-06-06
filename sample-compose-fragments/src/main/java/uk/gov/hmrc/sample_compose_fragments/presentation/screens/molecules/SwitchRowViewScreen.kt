package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel

@Composable
fun SwitchRowViewScreen() {

    val viewModel = viewModel<SwitchRowViewModel>()

    ScreenScrollViewColumn {
        PlaceholderSlot {
            SwitchRowView(
                title = stringResource(R.string.switch_row_placeholder_title),
                body = stringResource(viewModel.placeholderBodyText.collectAsStateWithLifecycle().value),
                onCheckedChangeListener = { enabled -> viewModel.onPlaceholderSwitchChanged(enabled) },
            )
        }

        ExamplesSlot {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                SwitchRowView(
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    title = stringResource(R.string.switch_row_example_1_title),
                    body = stringResource(R.string.switch_row_example_1_body),
                    onCheckedChangeListener = { enabled -> viewModel.onSwitchChanged(enabled)},
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                SwitchRowView(
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    title = stringResource(R.string.switch_row_example_2_title),
                    body = stringResource(R.string.switch_row_example_2_body),
                    checkedState = true,
                    onCheckedChangeListener = { enabled -> viewModel.onSwitchChanged(enabled)},
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                SwitchRowView(
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    body = stringResource(R.string.switch_row_example_3_title),
                    onCheckedChangeListener = { enabled -> viewModel.onSwitchChanged(enabled)},
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                SwitchRowView(
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    title = stringResource(R.string.switch_row_example_4_title),
                    onCheckedChangeListener = { enabled -> viewModel.onSwitchChanged(enabled)},
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                SwitchRowView(
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    title = stringResource(R.string.switch_row_example_5_title),
                    body = stringResource(R.string.switch_row_example_5_body),
                    onCheckedChangeListener = { enabled -> viewModel.onSwitchChanged(enabled)},
                )
            }
        }
    }
}
