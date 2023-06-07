package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel.SwitchRowExample

@Composable
fun SwitchRowViewScreen(viewModel: SwitchRowViewModel) {
    val placeholderSwitchUiState by viewModel.placeholderSwitchUiState.collectAsStateWithLifecycle()
    val examplesUiState by viewModel.examplesUiState.collectAsStateWithLifecycle()

    SwitchRowViewScreen(
        placeholderUiState = placeholderSwitchUiState,
        onPlaceholderSwitchChanged = { enabled -> viewModel.onPlaceholderSwitchChanged(enabled) },
        examplesUiState = examplesUiState,
        onExampleSwitchChanged = { enabled, example -> viewModel.onExampleSwitchChanged(enabled, example) }
    )
}


@Composable
fun SwitchRowViewScreen(
    placeholderUiState: SwitchRowViewModel.SwitchUiState,
    onPlaceholderSwitchChanged: (Boolean) -> Unit,
    examplesUiState: SwitchRowViewModel.ExamplesUiState,
    onExampleSwitchChanged: (Boolean, SwitchRowExample) -> Unit
) {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            SwitchRowView(
                title = placeholderUiState.title?.let { stringResource(it) },
                body = placeholderUiState.body?.let { stringResource(it) },
                checkedState = placeholderUiState.enabled,
                onCheckedChangeListener = { enabled -> onPlaceholderSwitchChanged(enabled) },
            )
        }

        ExamplesSlot {
            // Example one
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                with(examplesUiState.exampleOne) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedStateContentDesc = enabledContentDesc?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.ONE) },
                    )
                }
            }

            // Example two
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                with(examplesUiState.exampleTwo) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.TWO)},
                    )
                }
            }

            // Example three
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                with(examplesUiState.exampleThree) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.THREE)},
                    )
                }
            }

            // Example four
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                with(examplesUiState.exampleFour) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.FOUR)}
                    )
                }
            }

            // Example five
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                with(examplesUiState.exampleOne) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.FIVE)}
                    )
                }
            }
        }
    }
}
