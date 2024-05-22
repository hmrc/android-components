package uk.gov.hmrc.components.compose.molecule.donut

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun DonutChartView(
    values: List<Double>,
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        HmrcTheme.colors.hmrcDonutChartColor1,
        HmrcTheme.colors.hmrcDonutChartColor2,
        HmrcTheme.colors.hmrcPink
    ),
    shouldAnimate: Boolean = true,
    strokeWidth: Dp = HmrcTheme.dimensions.hmrcSpacing16,
) {
    val defaultStroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx())
    }

    val segments = processSegments(values, colors, defaultStroke)

    if (shouldAnimate) {
        val segment1Value = remember { Animatable(0f) }
        val segment2Value = remember { Animatable(0f) }
        val segment3Value = remember { Animatable(0f) }
        LaunchedEffect(values) {
            segment1Value.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
            )
            segment2Value.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 600, delayMillis = 1000, easing = FastOutSlowInEasing)
            )
            segment3Value.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 600, delayMillis = 1400, easing = FastOutSlowInEasing)
            )
        }

        Canvas(modifier.size(100.dp)) {
            segments.elementAtOrNull(0)?.let { drawDonutSegment(it.color, it.sweep * segment1Value.value, it.stroke) }
            segments.elementAtOrNull(1)?.let { drawDonutSegment(it.color, it.sweep * -segment2Value.value, it.stroke) }
            segments.elementAtOrNull(2)?.let { drawDonutSegment(it.color, it.sweep * -segment3Value.value, it.stroke) }
        }
    } else {
        Canvas(modifier.size(100.dp)) {
            processSegments(values, colors, defaultStroke).forEach { segment ->
                drawDonutSegment(segment.color, segment.sweep, segment.stroke)
            }
        }
    }
}

private fun processSegments(
    values: List<Double>,
    colors: List<Color>,
    stroke: Stroke,
): List<ProcessedSegment> {
    val totalValue = values.sum()
    val sortedSegments = values.sortedDescending()
    val processedSegments = mutableListOf<ProcessedSegment>()
    val wholeCirclePercent = 1 / totalValue
    var sweepStartPoint = 360f
    sortedSegments.forEachIndexed { index, value ->
        val color = colors[index]
        val sweepPercent = wholeCirclePercent * value
        val sweepSpread = (sweepPercent * 360).toFloat()

        processedSegments.add(ProcessedSegment(color, sweepStartPoint, stroke))

        sweepStartPoint -= sweepSpread
    }
    return processedSegments
}

private class ProcessedSegment(val color: Color, val sweep: Float, val stroke: Stroke)

private fun DrawScope.drawDonutSegment(color: Color, sweep: Float, stroke: Stroke) {
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = 270f, // Start at the top
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}

@Preview(showBackground = true)
@Composable
fun DonutChartViewPreview() {
    HmrcTheme {
        DonutChartView(
            listOf(10.0)
        )
    }
}