/*
 * Copyright 2024 HM Revenue & Customs
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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartView
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewInput
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewKeyItem
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewOutput
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewSegmentStyle
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewStrokeType
import uk.gov.hmrc.components.compose.molecule.donut.KeyContent
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.container.SeparatedViewContainer
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.components.compose.ui.theme.LocalOrientationMode
import uk.gov.hmrc.components.compose.ui.theme.Orientation
import uk.gov.hmrc.ptcalc.common.compose.core.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun DonutChartViewScreen() {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val input = listOf(
                    DonutChartViewInput(34.0, KeyContent("£34", "Item")),
                    DonutChartViewInput(33.0, KeyContent("£33", "Item")),
                    DonutChartViewInput(33.0, KeyContent("£33", "Item"))
                )
                DonutChartView(input, Modifier.size(200.dp)) { values ->
                    values.forEach { output ->
                        Spacer(modifier = Modifier.height(dimensions.hmrcSpacing4))
                        DonutChartViewKeyItem(
                            modifier = Modifier.padding(dimensions.hmrcSpacing16),
                            donutOutput = output
                        )
                    }
                }
            }
        }
        ExamplesSlot {
            /**
             * This example is contains Donut chart and Donut Items view and the UI is changed based on the orientation
             */
            HmrcCardView {
                val outputValues = remember { mutableStateOf(listOf<DonutChartViewOutput>()) }
                val input = listOf(
                    DonutChartViewInput(50.0, KeyContent("£50", "Item")),
                    DonutChartViewInput(200.0, KeyContent("£200", "Item")),
                    DonutChartViewInput(50.0, KeyContent("£50", "Item"))
                )
                if (LocalOrientationMode.current == Orientation.Landscape) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            val donutChartViewKeyItemList = mutableListOf<@Composable () -> Unit>()
                            outputValues.value.forEach { donutChartViewOutput ->
                                donutChartViewKeyItemList.add @Composable {
                                    DonutChartViewKeyItem(
                                        modifier = Modifier.padding(dimensions.hmrcSpacing16),
                                        donutOutput = donutChartViewOutput
                                    )
                                }
                            }
                            SeparatedViewContainer(
                                modifier = Modifier,
                                showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                                dividerHorizontalPadding = dimensions.hmrcSpacing16,
                                views = donutChartViewKeyItemList
                            )
                        }
                        DonutChartView(
                            input = input,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(dimensions.hmrcSpacing16)
                        ) { values ->
                            LaunchedEffect(Unit) { outputValues.value = values }
                        }
                    }
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        DonutChartView(
                            input = input,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(dimensions.hmrcSpacing16)
                        ) { values ->
                            LaunchedEffect(Unit) { outputValues.value = values }
                        }

                        Column {
                            val donutChartViewKeyItemList = mutableListOf<@Composable () -> Unit>()
                            outputValues.value.forEach { donutChartViewOutput ->
                                donutChartViewKeyItemList.add @Composable {
                                    DonutChartViewKeyItem(
                                        modifier = Modifier.padding(dimensions.hmrcSpacing16),
                                        donutOutput = donutChartViewOutput
                                    )
                                }
                            }
                            SeparatedViewContainer(
                                modifier = Modifier,
                                showDivider = SeparatedViewContainer.DividerMode.SHOW_DIVIDER_MIDDLE,
                                dividerHorizontalPadding = dimensions.hmrcSpacing16,
                                views = donutChartViewKeyItemList
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimensions.hmrcSpacing16))
            HmrcCardView {
                DonutChartView(
                    listOf(
                        DonutChartViewInput(34.0, KeyContent("£34", "Item")),
                        DonutChartViewInput(33.0, KeyContent("£33", "Item")),
                        DonutChartViewInput(33.0, KeyContent("£33", "Item"))
                    ),
                    Modifier.size(100.dp),
                    shouldAnimate = false
                ) { values ->
                    values.forEach { output ->
                        Spacer(modifier = Modifier.height(dimensions.hmrcSpacing4))
                        DonutChartViewKeyItem(donutOutput = output)
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimensions.hmrcSpacing16))
            HmrcCardView {
                DonutChartView(
                    listOf(
                        DonutChartViewInput(75.0, KeyContent("£75", "Item")),
                        DonutChartViewInput(20.0, KeyContent("£20", "Item")),
                        DonutChartViewInput(2.0, KeyContent("£2", "Item"))
                    ),
                    Modifier.fillMaxWidth(0.75f)
                ) { values ->
                    values.forEach { output ->
                        Spacer(modifier = Modifier.height(dimensions.hmrcSpacing4))
                        DonutChartViewKeyItem(
                            modifier = Modifier.padding(dimensions.hmrcSpacing16),
                            donutOutput = output
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimensions.hmrcSpacing16))
            HmrcCardView {
                DonutChartView(
                    listOf(
                        DonutChartViewInput(100.0, KeyContent("£100", "Item")),
                        DonutChartViewInput(100.0, KeyContent("£100", "Item")),
                    ),
                    Modifier.fillMaxWidth(1f),
                    styles = listOf(
                        DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcPink),
                        DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcYellow)
                    )
                ) { values ->
                    values.forEach { output ->
                        Spacer(modifier = Modifier.height(dimensions.hmrcSpacing4))
                        DonutChartViewKeyItem(
                            modifier = Modifier.padding(dimensions.hmrcSpacing16),
                            donutOutput = output
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimensions.hmrcSpacing16))
            HmrcCardView {
                DonutChartView(
                    listOf(
                        DonutChartViewInput(50.0, KeyContent("£50", "Item")),
                        DonutChartViewInput(200.0, KeyContent("£200", "Item")),
                        DonutChartViewInput(50.0, KeyContent("£50", "Item"))
                    ),
                    styles = listOf(
                        DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcPink),
                        DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcYellow),
                        DonutChartViewSegmentStyle(
                            HmrcTheme.colors.hmrcGreen1,
                            strokeType = DonutChartViewStrokeType.STRIPE
                        )
                    )
                ) { values ->
                    values.forEach { output ->
                        DonutChartViewKeyItem(
                            modifier = Modifier.padding(dimensions.hmrcSpacing16),
                            donutOutput = output
                        )
                    }
                }
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun DonutChartViewScreenPreview() {
    HmrcTheme {
        HmrcSurface {
            DonutChartViewScreen()
        }
    }
}

