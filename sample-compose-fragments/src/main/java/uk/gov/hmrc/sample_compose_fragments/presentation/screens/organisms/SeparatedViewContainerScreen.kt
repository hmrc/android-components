package uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer.SHOW_DIVIDER_ALL
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer.SHOW_DIVIDER_BEGINNING
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer.SHOW_DIVIDER_END
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer.SHOW_DIVIDER_MIDDLE
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SeparatedViewContainerViewModel

object SeparatedViewContainerScreen {

    @Composable
    operator fun invoke(
        viewModel: SeparatedViewContainerViewModel,
    ) {
        val examplesUiState by viewModel.examplesUiState.collectAsStateWithLifecycle()

        ScreenScrollViewColumn {
            PlaceholderSlot {
                SeparatedViewContainer(
                    showDivider = SHOW_DIVIDER_ALL,
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                            modifier = Modifier
                                .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    }
                )
            }

            PlaceholderSlot(showHeading = false) {
                SeparatedViewContainer(
                    showDivider = SHOW_DIVIDER_MIDDLE,
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                            modifier = Modifier
                                .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    },
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                            modifier = Modifier
                                .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    },
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                            modifier = Modifier
                                .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    }
                )
            }

            ExamplesSlot {
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        showDivider = SHOW_DIVIDER_ALL,
                        {
                            with(examplesUiState.exampleOne) {
                                SwitchRowView(
                                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                                    title = title?.let { stringResource(it) },
                                    body = body?.let { stringResource(it) },
                                    checkedStateContentDesc = enabledContentDesc?.let { stringResource(it) },
                                    checkedState = enabled,
                                    onCheckedChangeListener = { enabled ->
                                        viewModel.onExampleSwitchChanged(
                                            enabled,
                                            SeparatedViewContainerViewModel.SwitchRowExample.ONE
                                        )
                                    }
                                )
                            }
                        },
                        {
                            with(examplesUiState.exampleTwo) {
                                SwitchRowView(
                                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                                    title = title?.let { stringResource(it) },
                                    body = body?.let { stringResource(it) },
                                    checkedState = enabled,
                                    onCheckedChangeListener = { enabled ->
                                        viewModel.onExampleSwitchChanged(
                                            enabled,
                                            SeparatedViewContainerViewModel.SwitchRowExample.TWO
                                        )
                                    }
                                )
                            }
                        },
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        showDivider = SHOW_DIVIDER_BEGINNING,
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        showDivider = SHOW_DIVIDER_MIDDLE,
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        showDivider = SHOW_DIVIDER_END,
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                                modifier = Modifier
                                    .padding(HmrcTheme.dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                    )
                }
            }
        }
    }
}
