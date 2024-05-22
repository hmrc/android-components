package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.hmrc.components.compose.molecule.donut.DonutChartView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot

@Composable
fun DonutChartViewScreen() {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            DonutChartView(listOf(34.0, 33.0, 33.0))
        }
        ExamplesSlot {
            DonutChartView(listOf(75.0, 20.0, 2.0))
            Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(listOf(100.0, 100.0))
            Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(listOf(200.0, 50.0, 50.0))
            Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing16))
            DonutChartView(listOf(34.0, 33.0, 33.0))
        }
    }
}