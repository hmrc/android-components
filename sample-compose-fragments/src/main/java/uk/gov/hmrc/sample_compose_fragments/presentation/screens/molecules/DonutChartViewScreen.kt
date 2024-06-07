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
                DonutChartView(listOf(34.0, 33.0, 33.0), Modifier.size(200.dp))
            }
        }
        ExamplesSlot {
            DonutChartView(listOf(34.0, 33.0, 33.0), Modifier.size(100.dp), shouldAnimate = false)
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(listOf(75.0, 20.0, 2.0), Modifier.fillMaxWidth(0.75f))
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(
                listOf(100.0, 100.0),
                Modifier.fillMaxWidth(1f),
                styles = listOf(
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcPink),
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcYellow)
                )
            )
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(
                listOf(200.0, 50.0, 50.0),
                styles = listOf(
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcPink),
                    DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcYellow),
                    DonutChartViewSegmentStyle(
                        HmrcTheme.colors.hmrcGreen1,
                        strokeType = DonutChartViewStrokeType.STRIPE
                    )
                )
            )
        }
    }
}