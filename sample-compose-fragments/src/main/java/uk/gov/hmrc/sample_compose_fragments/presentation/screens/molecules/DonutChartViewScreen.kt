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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartView
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewInput
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewKeyItem
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewSegmentStyle
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartViewStrokeType
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot

@Composable
fun DonutChartViewScreen() {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val input = listOf(
                    DonutChartViewInput(34.0, "Item: 34"),
                    DonutChartViewInput(33.0, "Item: 33"),
                    DonutChartViewInput(33.0, "Item: 33")
                )
                val values = DonutChartView(input, Modifier.size(200.dp))
                values.forEach { output ->
                    Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing4))
                    DonutChartViewKeyItem(donutOutput = output)
                }
            }
        }
        ExamplesSlot {
            DonutChartView(
                listOf(
                    DonutChartViewInput(34.0, "Item: 34"),
                    DonutChartViewInput(33.0, "Item: 33"),
                    DonutChartViewInput(33.0, "Item: 33")
                ),
                Modifier.size(100.dp),
                shouldAnimate = false
            ).forEach { output ->
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing4))
                DonutChartViewKeyItem(donutOutput = output)
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(
                listOf(
                    DonutChartViewInput(75.0, "Item: 75"),
                    DonutChartViewInput(20.0, "Item: 20"),
                    DonutChartViewInput(2.0, "Item: 2")
                ),
                Modifier.fillMaxWidth(0.75f)
            ).forEach { output ->
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing4))
                DonutChartViewKeyItem(donutOutput = output)
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(
                listOf(
                    DonutChartViewInput(100.0, "Item: 100"),
                    DonutChartViewInput(100.0, "Item: 100"),
                ),
                Modifier.fillMaxWidth(1f),
                styles = listOf(
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcPink),
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcYellow)
                )
            ).forEach { output ->
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing4))
                DonutChartViewKeyItem(donutOutput = output)
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(
                listOf(
                    DonutChartViewInput(50.0, "Item: 50"),
                    DonutChartViewInput(200.0, "Item: 200"),
                    DonutChartViewInput(50.0, "Item: 50")
                ),
                styles = listOf(
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcPink),
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcYellow),
                    DonutChartViewSegmentStyle(
                        HmrcTheme.colors.hmrcGreen1,
                        strokeType = DonutChartViewStrokeType.STRIPE
                    )
                )
            ).forEach { output ->
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing4))
                DonutChartViewKeyItem(donutOutput = output)
            }
        }
    }
}