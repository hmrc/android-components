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
import androidx.compose.ui.graphics.PathEffect
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
    styles: List<DonutChartViewSegmentStyle> = listOf(
        DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcDonutChartColor1),
        DonutChartViewSegmentStyle(HmrcTheme.colors.hmrcDonutChartColor2),
        DonutChartViewSegmentStyle(
            HmrcTheme.colors.hmrcPink,
            HmrcTheme.colors.hmrcDonutChartColor2,
            DonutChartViewStrokeType.STRIPE
        )
    ),
    shouldAnimate: Boolean = true,
    strokeWidth: Dp = HmrcTheme.dimensions.hmrcSpacing16,
) {
    require(styles.size >= values.size) { "There are not enough styles defined for all values." }

    val solidStroke = with(LocalDensity.current) { Stroke(width = strokeWidth.toPx()) }
    val stripedStroke = with(LocalDensity.current) {
        Stroke(
            width = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(HmrcTheme.dimensions.hmrcSpacing4.toPx(), HmrcTheme.dimensions.hmrcSpacing4.toPx())
            )
        )
    }

    fun DonutChartViewStrokeType.toStroke() = when (this) {
        DonutChartViewStrokeType.SOLID -> solidStroke
        DonutChartViewStrokeType.STRIPE -> stripedStroke
    }

    val segments = processSegments(values, styles)
    val baseColor = HmrcTheme.colors.hmrcWhite

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
                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
            )
            segment3Value.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
            )
        }

        Canvas(modifier.size(100.dp)) {
            segments.elementAtOrNull(0)?.let {
                drawSegment(baseColor, solidStroke, it.color, it.sweep * segment1Value.value, it.stroke.toStroke())
            }
            segments.elementAtOrNull(1)?.let {
                drawSegment(baseColor, solidStroke, it.color, it.sweep * -segment2Value.value, it.stroke.toStroke())
            }
            segments.elementAtOrNull(2)?.let {
                drawSegment(baseColor, solidStroke, it.color, it.sweep * -segment3Value.value, it.stroke.toStroke())
            }
        }
    } else {
        Canvas(modifier.size(100.dp)) {
            processSegments(values, styles).forEachIndexed { index,  segment ->
                if (index == 0) {
                    drawSegment(baseColor, solidStroke, segment.color, segment.sweep, segment.stroke.toStroke())
                } else {
                    drawSegment(baseColor, solidStroke, segment.color, -segment.sweep, segment.stroke.toStroke())
                }
            }
        }
    }
}

private fun processSegments(
    values: List<Double>,
    styles: List<DonutChartViewSegmentStyle>,
): List<ProcessedSegment> {
    val totalValue = values.sum()
    val sortedSegments = values.sortedDescending()
    val processedSegments = mutableListOf<ProcessedSegment>()
    val wholeCirclePercent = 1 / totalValue
    var sweepStartPoint = 360f
    sortedSegments.forEachIndexed { index, value ->
        val style = styles[index]
        val sweepPercent = wholeCirclePercent * value
        val sweepSpread = (sweepPercent * 360).toFloat()

        val (strokeColor, strokeType) = if (style.strokeType == DonutChartViewStrokeType.SOLID || sweepPercent <= 0.05) {
            Pair(style.solidColor, DonutChartViewStrokeType.SOLID)
        } else Pair(style.stripeColor, DonutChartViewStrokeType.STRIPE)

        processedSegments.add(ProcessedSegment(strokeColor, sweepStartPoint, strokeType))

        sweepStartPoint -= sweepSpread
    }
    return processedSegments
}

private class ProcessedSegment(val color: Color, val sweep: Float, val stroke: DonutChartViewStrokeType)

private fun DrawScope.drawSegment(
    baseColor: Color,
    baseStroke: Stroke,
    color: Color,
    sweep: Float,
    stroke: Stroke
) {
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = baseColor,
        startAngle = 270f, // Start at the top
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = baseStroke
    )
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

class DonutChartViewSegmentStyle(
    val solidColor: Color,
    val stripeColor: Color = solidColor,
    val strokeType: DonutChartViewStrokeType = DonutChartViewStrokeType.SOLID,
)
enum class DonutChartViewStrokeType { SOLID, STRIPE }

@Preview(showBackground = true)
@Composable
fun DonutChartViewPreview() {
    HmrcTheme {
        DonutChartView(
            listOf(10.0)
        )
    }
}