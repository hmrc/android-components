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
package uk.gov.hmrc.components.compose.molecule.donut

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowItem
import uk.gov.hmrc.components.compose.molecule.multiColumnRowView.MultiColumnRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

class DonutChartViewInput(val value: Double, val formattedValue: String, val label: String)
class DonutChartViewSegmentStyle(
    val solidColor: Color,
    val stripeColor: Color = solidColor,
    val strokeType: DonutChartViewStrokeType = DonutChartViewStrokeType.SOLID,
)

enum class DonutChartViewStrokeType { SOLID, STRIPE }
class DonutChartViewOutput(
    val color: Color,
    val label: String,
    val value: Double,
    val formattedValue: String,
    val sweep: Float,
    val stroke: DonutChartViewStrokeType
)

object DonutChartView {
    private const val FIRST_SEGMENT_POSITION = 0
    private const val SECOND_SEGMENT_POSITION = 1
    private const val THIRD_SEGMENT_POSITION = 2
    private const val FIRST_SEGMENT_ANIMATION_DURATION = 1200
    private const val SECOND_SEGMENT_ANIMATION_DURATION = 600
    private const val THIRD_SEGMENT_ANIMATION_DURATION = 600
    private const val ANIMATION_START_VALUE = 0f
    private const val ANIMATION_END_VALUE = 1f
    private const val TOP_OF_CIRCLE_DEGREES = 270f
    private const val COMPLETE_CIRCLE_DEGREES = 360f
    private const val WHOLE_PERCENT = 1f
    private const val MIN_PERCENT_FOR_STRIPES = 0.05

    @Composable
    operator fun invoke(
        input: List<DonutChartViewInput>,
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
        onDonutChartViewOutputReady: @Composable (List<DonutChartViewOutput>) -> Unit
    ) {
        require(styles.size >= input.size) { "There are not enough styles defined for all input values." }

        val canvasModifier = modifier.aspectRatio(1f)
        val baseColor = HmrcTheme.colors.hmrcWhite
        val solidStroke = with(LocalDensity.current) { Stroke(width = strokeWidth.toPx()) }
        val stripedStroke = with(LocalDensity.current) {
            Stroke(width = strokeWidth.toPx(), pathEffect = stripedPathEffect(HmrcTheme.dimensions.hmrcSpacing4.toPx()))
        }

        fun DonutChartViewStrokeType.toStroke() = when (this) {
            DonutChartViewStrokeType.SOLID -> solidStroke
            DonutChartViewStrokeType.STRIPE -> stripedStroke
        }

        val segments = processSegments(input, styles)

        if (shouldAnimate) {
            val segment1Value = remember { Animatable(ANIMATION_START_VALUE) }
            val segment2Value = remember { Animatable(ANIMATION_START_VALUE) }
            val segment3Value = remember { Animatable(ANIMATION_START_VALUE) }
            LaunchedEffect(input) {
                segment1Value.animateTo(
                    targetValue = ANIMATION_END_VALUE,
                    animationSpec = tween(
                        durationMillis = FIRST_SEGMENT_ANIMATION_DURATION, easing = FastOutSlowInEasing
                    )
                )
                segment2Value.animateTo(
                    targetValue = ANIMATION_END_VALUE,
                    animationSpec = tween(
                        durationMillis = SECOND_SEGMENT_ANIMATION_DURATION, easing = FastOutSlowInEasing
                    )
                )
                segment3Value.animateTo(
                    targetValue = ANIMATION_END_VALUE,
                    animationSpec = tween(
                        durationMillis = THIRD_SEGMENT_ANIMATION_DURATION, easing = FastOutSlowInEasing
                    )
                )
            }

            Canvas(canvasModifier) {
                segments.elementAtOrNull(FIRST_SEGMENT_POSITION)?.let {
                    drawSegment(baseColor, solidStroke, it.color, it.sweep * segment1Value.value, it.stroke.toStroke())
                }
                segments.elementAtOrNull(SECOND_SEGMENT_POSITION)?.let {
                    drawSegment(baseColor, solidStroke, it.color, it.sweep * -segment2Value.value, it.stroke.toStroke())
                }
                segments.elementAtOrNull(THIRD_SEGMENT_POSITION)?.let {
                    drawSegment(baseColor, solidStroke, it.color, it.sweep * -segment3Value.value, it.stroke.toStroke())
                }
            }
        } else {
            Canvas(canvasModifier) {
                segments.forEachIndexed { index, segment ->
                    if (index == FIRST_SEGMENT_POSITION) {
                        drawSegment(baseColor, solidStroke, segment.color, segment.sweep, segment.stroke.toStroke())
                    } else {
                        drawSegment(baseColor, solidStroke, segment.color, -segment.sweep, segment.stroke.toStroke())
                    }
                }
            }
        }
        onDonutChartViewOutputReady(segments)
    }

    private fun processSegments(
        input: List<DonutChartViewInput>,
        styles: List<DonutChartViewSegmentStyle>
    ): List<DonutChartViewOutput> {
        val totalValue = input.sumOf { it.value }
        val sortedSegments = input.sortedByDescending { it.value }
        val processedSegments = mutableListOf<DonutChartViewOutput>()
        val wholeCirclePercent = WHOLE_PERCENT / totalValue
        var sweepStartPoint = COMPLETE_CIRCLE_DEGREES
        sortedSegments.forEachIndexed { index, inputItem ->
            val style = styles[index]
            val sweepPercent = wholeCirclePercent * inputItem.value
            val sweepSpread = (sweepPercent * COMPLETE_CIRCLE_DEGREES).toFloat()

            val (strokeColor, strokeType) = if (
                style.strokeType == DonutChartViewStrokeType.SOLID || sweepPercent <= MIN_PERCENT_FOR_STRIPES
            ) {
                Pair(style.solidColor, DonutChartViewStrokeType.SOLID)
            } else Pair(style.stripeColor, DonutChartViewStrokeType.STRIPE)

            processedSegments.add(
                DonutChartViewOutput(
                    color = strokeColor,
                    label = inputItem.label,
                    value = inputItem.value,
                    formattedValue = inputItem.formattedValue,
                    sweep = sweepStartPoint,
                    stroke = strokeType
                )
            )
            sweepStartPoint -= sweepSpread
        }
        return processedSegments
    }

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
            startAngle = TOP_OF_CIRCLE_DEGREES, // Start at the top
            sweepAngle = sweep,
            useCenter = false,
            topLeft = Offset(diameterOffset, diameterOffset),
            size = Size(arcDimen, arcDimen),
            style = baseStroke
        )
        drawArc(
            color = color,
            startAngle = TOP_OF_CIRCLE_DEGREES, // Start at the top
            sweepAngle = sweep,
            useCenter = false,
            topLeft = Offset(diameterOffset, diameterOffset),
            size = Size(arcDimen, arcDimen),
            style = stroke
        )
    }
}

private fun stripedPathEffect(dashSize: Float) = PathEffect.dashPathEffect(floatArrayOf(dashSize, dashSize))

@Composable
fun DonutChartViewKeyItem(donutOutput: DonutChartViewOutput, modifier: Modifier = Modifier) {
    val stripes = with(LocalDensity.current) { stripedPathEffect(HmrcTheme.dimensions.hmrcSpacing4.toPx()) }
    val keyIndicatorSize = HmrcTheme.dimensions.hmrcIconSize24
    val baseColor = HmrcTheme.colors.hmrcWhite

    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(keyIndicatorSize)) {
            when (donutOutput.stroke) {
                DonutChartViewStrokeType.SOLID -> drawRect(donutOutput.color)
                DonutChartViewStrokeType.STRIPE -> {
                    drawRect(baseColor, style = Fill)
                    drawRect(donutOutput.color, style = Stroke())
                    drawLine(
                        color = donutOutput.color,
                        start = Offset(0f, size.height / 2),
                        end = Offset(size.width, size.height / 2),
                        strokeWidth = keyIndicatorSize.toPx(),
                        pathEffect = stripes
                    )
                }
            }
        }
        Spacer(modifier = modifier.width(HmrcTheme.dimensions.hmrcSpacing16))
        MultiColumnRowView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = HmrcTheme.dimensions.hmrcSpacing16),
            columnList = listOf(
                MultiColumnRowItem(text = donutOutput.label),
                MultiColumnRowItem(text = donutOutput.formattedValue)
            )
        )
    }
}
