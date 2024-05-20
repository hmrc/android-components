package uk.gov.hmrc.components.compose.molecule.donut

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun DonutChartView(
    segments: List<DonutChartSegment>,
    modifier: Modifier = Modifier,
    shouldAnimate: Boolean = true,
    strokeWidth: Dp = HmrcTheme.dimensions.hmrcSpacing16,
) {
    val defaultColorList = listOf(
        HmrcTheme.colors.hmrcDonutChartColor1,
        HmrcTheme.colors.hmrcDonutChartColor2,
        HmrcTheme.colors.hmrcDonutChartColor3
    )
    val defaultStroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx())
    }
    Canvas(modifier.size(100.dp)) {
        segments.processSegments(defaultColorList, defaultStroke).forEach { segment ->
            drawDonutSegment(segment.color, segment.sweep, segment.stroke)
        }
    }
}

private fun List<DonutChartSegment>.processSegments(
    defaultColors: List<Color>,
    defaultStroke: Stroke,
): List<ProcessedSegment> {
    val totalValue = sumOf { it.value }
    val sortedSegments = sortedByDescending { it.value }
    val processedSegments = mutableListOf<ProcessedSegment>()
    sortedSegments.forEachIndexed { index, segment ->
        val color = segment.customColor ?: defaultColors[index]
        val sweep = ((1 / totalValue) * segment.value).toFloat()
        val stroke = segment.customStroke ?: defaultStroke

        processedSegments.add(ProcessedSegment(color, sweep, stroke))
    }
    return processedSegments
}

private class ProcessedSegment(val color: Color, val sweep: Float, val stroke: Stroke)

private fun DrawScope.drawDonutSegment(color: Color, sweep: Float, stroke: Stroke) {
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = 270f, // Start at 12 o'clock
        sweepAngle = sweep * 360f,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}

data class DonutChartSegment(val value: Double, val customColor: Color? = null, val customStroke: Stroke? = null)