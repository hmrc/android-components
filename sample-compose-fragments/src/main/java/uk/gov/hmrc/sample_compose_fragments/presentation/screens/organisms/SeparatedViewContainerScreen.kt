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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer2
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
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
                    modifier = Modifier,
                    showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_ALL,
                    dividerHorizontalPadding = 0.dp,
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                            modifier = Modifier
                                .padding(dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    }
                )
            }

            PlaceholderSlot(showHeading = false) {
                SeparatedViewContainer(
                    modifier = Modifier,
                    showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                    dividerHorizontalPadding = 0.dp,
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                            modifier = Modifier
                                .padding(dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    },
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                            modifier = Modifier
                                .padding(dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    },
                    {
                        Text(
                            text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                            modifier = Modifier
                                .padding(dimensions.hmrcSpacing8)
                                .fillMaxSize(),
                            style = typography.h5,
                        )
                    }
                )
            }

            ExamplesSlot {
                HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        modifier = Modifier.padding(vertical = dimensions.hmrcSpacing16),
                        showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_ALL,
                        dividerHorizontalPadding = dimensions.hmrcSpacing16,
                        {
                            with(examplesUiState.exampleOne) {
                                SwitchRowView(
                                    modifier = Modifier.padding(dimensions.hmrcSpacing16),
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
                                    modifier = Modifier.padding(dimensions.hmrcSpacing16),
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
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        modifier = Modifier.padding(dimensions.hmrcSpacing16),
                        showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_BEGINNING,
                        dividerHorizontalPadding = 0.dp,
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        modifier = Modifier,
                        showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                        dividerHorizontalPadding = dimensions.hmrcSpacing16,
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing16)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing16)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing16)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        }
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                    SeparatedViewContainer(
                        modifier = Modifier,
                        showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_END,
                        dividerHorizontalPadding = dimensions.hmrcSpacing8,
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_1),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_2),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        },
                        {
                            Text(
                                text = stringResource(id = R.string.separated_view_container_placeholder_text_3),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        }
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = dimensions.hmrcSpacing16)) {
                    val itemLists = listOf(
                        R.string.separated_view_container_placeholder_text_1,
                        R.string.separated_view_container_placeholder_text_2,
                        R.string.separated_view_container_placeholder_text_3
                    )

                    val composableList = mutableListOf<@Composable () -> Unit>()

                    itemLists.forEach { item ->
                        composableList.add @Composable {
                            Text(
                                text = stringResource(id = item),
                                modifier = Modifier
                                    .padding(dimensions.hmrcSpacing8)
                                    .fillMaxSize(),
                                style = typography.h5,
                            )
                        }
                    }
                    SeparatedViewContainer2(
                        modifier = Modifier,
                        showDivider = SeparatedViewContainer2.DividerMode.SHOW_DIVIDER_MIDDLE,
                        dividerHorizontalPadding = dimensions.hmrcSpacing8,
                        views = composableList
                    )
                }
            }
        }
    }
}
