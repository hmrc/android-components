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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HMRCPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel.ExamplesUiState
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel.SwitchRowExample
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel.SwitchUiState

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
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
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
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                with(examplesUiState.exampleTwo) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.TWO) },
                    )
                }
            }

            // Example three
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                with(examplesUiState.exampleThree) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled ->
                            onExampleSwitchChanged(
                                enabled,
                                SwitchRowExample.THREE
                            )
                        },
                    )
                }
            }

            // Example four
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                with(examplesUiState.exampleFour) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.FOUR) }
                    )
                }
            }

            // Example five
            HmrcCardView() {
                with(examplesUiState.exampleFive) {
                    SwitchRowView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = title?.let { stringResource(it) },
                        body = body?.let { stringResource(it) },
                        checkedState = enabled,
                        onCheckedChangeListener = { enabled -> onExampleSwitchChanged(enabled, SwitchRowExample.FIVE) }
                    )
                }
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun SwitchRowViewScreenPreview() {
    HMRCPreview {
        SwitchRowViewScreen(
            placeholderUiState = SwitchUiState(
                title = R.string.switch_row_placeholder_title,
                body = R.string.switch_row_placeholder_body_off,
                enabled = false,
            ),
            examplesUiState = ExamplesUiState(
                exampleOne = SwitchUiState(
                    title = R.string.switch_row_example_1_title,
                    body = R.string.switch_row_example_1_body,
                    enabled = false,
                    enabledContentDesc = R.string.switch_row_example_1_disabled_content_desc,
                ),
                exampleTwo = SwitchUiState(
                    title = R.string.switch_row_example_2_title,
                    body = R.string.switch_row_example_2_body,
                    enabled = true,
                ),
                exampleThree = SwitchUiState(
                    title = null,
                    body = R.string.switch_row_example_3_title,
                    enabled = false,
                ),
                exampleFour = SwitchUiState(
                    title = R.string.switch_row_example_4_title,
                    body = null,
                    enabled = false,
                ),
                exampleFive = SwitchUiState(
                    title = R.string.switch_row_example_5_title,
                    body = R.string.switch_row_example_5_body,
                    enabled = false,
                )
            ),
            onExampleSwitchChanged = { _, _ -> },
            onPlaceholderSwitchChanged = {}
        )
    }
}
