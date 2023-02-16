/*
 * Copyright 2022 HM Revenue & Customs
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
package uk.gov.hmrc.components.molecule.donut

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import android.provider.Settings
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.Transformation
import androidx.core.content.ContextCompat
import uk.gov.hmrc.components.R
import kotlin.math.min

class DonutChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val color1: Int
    private val color2: Int
    private val color3: Int

    private val stripes1: Boolean
    private val stripes2: Boolean
    private val stripes3: Boolean

    private lateinit var color1Paint: Paint
    private lateinit var color2Paint: Paint
    private lateinit var color3Paint: Paint

    private val donutWidth = context.resources.getDimension(R.dimen.donut_width)
    private lateinit var rect: RectF
    private val startAngle: Float = LEFT_OF_CIRCLE
    private var value1SweepAngle: Float = 0f
    private var value2SweepAngle: Float = 0f
    private var value3SweepAngle: Float = 0f

    private var value1: Float = 0.0f
    private var value2: Float = 0.0f
    private var value3: Float = 0.0f
    private var shouldAnimate: Boolean = true

    var value1Percent: Float = 0.0f
    var value2Percent: Float = 0.0f
    var value3Percent: Float = 0.0f

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.DonutChartView, 0, 0).apply {
            try {
                color1 = getColor(
                    R.styleable.DonutChartView_color1,
                    ContextCompat.getColor(context, R.color.hmrc_donut_chart_color_1)
                )
                color2 = getColor(
                    R.styleable.DonutChartView_color2,
                    ContextCompat.getColor(context, R.color.hmrc_donut_chart_color_2)
                )
                color3 = getColor(
                    R.styleable.DonutChartView_color3,
                    ContextCompat.getColor(context, R.color.hmrc_donut_chart_color_3)
                )
                stripes1 = getBoolean(
                    R.styleable.DonutChartView_stripes1,
                    false
                )
                stripes2 = getBoolean(
                    R.styleable.DonutChartView_stripes2,
                    false
                )
                stripes3 = getBoolean(
                    R.styleable.DonutChartView_stripes3,
                    true
                )
            } finally {
                recycle()
            }
        }
    }

    private fun stripesOrPlain(stripes: Boolean, color: Int): Paint {
        return if (stripes) {
            donutPaintWithStripes(color)
        } else {
            donutPaintWithColour(color)
        }
    }

    private fun donutPaintWithColour(paintColor: Int) = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = donutWidth
        color = paintColor
    }

    private fun donutPaintWithStripes(paintColor1: Int): Paint {
        val width = rect.width()
        val height = rect.height()
        val diameter = min(width, height)
        val radius = diameter / 2F

        val circumference: Float = (2F * Math.PI * radius).toFloat()
        val dashPlusGapSize = (circumference / TOTAL_NUM_OF_DASHES)
        val intervals = floatArrayOf(dashPlusGapSize * INTERVAL_GAP, dashPlusGapSize * INTERVAL_GAP)

        val path = DashPathEffect(intervals, 0F)

        val paint2 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = donutWidth
            color = paintColor1
            pathEffect = path
        }
        return paint2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = resolveSizeAndState(suggestedMinimumWidth, widthMeasureSpec, 1)
        val h = resolveSizeAndState(MeasureSpec.getSize(w), heightMeasureSpec, 0)
        setMeasuredDimension(w, h)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val diameter = min(w, h)
        val strokeWidthOffset = donutWidth / 2

        rect = RectF(
            strokeWidthOffset,
            strokeWidthOffset,
            diameter - strokeWidthOffset,
            diameter - strokeWidthOffset
        ).apply {
            offsetTo(strokeWidthOffset, strokeWidthOffset)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        val white = stripesOrPlain(false, ContextCompat.getColor(context, R.color.hmrc_white_background))

        canvas?.apply {
            if (value1Percent < PERCENT_FOR_NO_STRIPES) {
                color1Paint = donutPaintWithColour(color1)
                drawArc(rect, startAngle, value1SweepAngle, false, color1Paint)
            } else {
                color1Paint = stripesOrPlain(stripes1, color1)
                drawArc(rect, startAngle, value1SweepAngle, false, white)
                drawArc(rect, startAngle, value1SweepAngle, false, color1Paint)
            }

            if (value2Percent < PERCENT_FOR_NO_STRIPES) {
                color2Paint = donutPaintWithColour(color2)
                drawArc(rect, startAngle, value2SweepAngle, false, color2Paint)
            } else {
                color2Paint = stripesOrPlain(stripes2, color2)
                drawArc(rect, startAngle, value2SweepAngle, false, white)
                drawArc(rect, startAngle, value2SweepAngle, false, color2Paint)
            }

            if (value3Percent < PERCENT_FOR_NO_STRIPES) {
                color3Paint = donutPaintWithColour(ContextCompat.getColor(context, R.color.hmrc_pink))
                drawArc(rect, startAngle, value3SweepAngle, false, color3Paint)
            } else {
                color3Paint = stripesOrPlain(stripes3, color3)
                drawArc(rect, startAngle, value3SweepAngle, false, white)
                drawArc(rect, startAngle, value3SweepAngle, false, color3Paint)
            }
        }
    }

    fun setValues(value1: Float, value2: Float, value3: Float = 0f, shouldAnimate: Boolean) {
        this@DonutChartView.value1 = value1
        this@DonutChartView.value2 = value2
        this@DonutChartView.value3 = value3
        this@DonutChartView.shouldAnimate = shouldAnimate

        val total = value1 + value2 + value3
        this@DonutChartView.value3Percent = (ONE_HUNDRED_PERCENT / total) * value3
        this@DonutChartView.value2Percent = (ONE_HUNDRED_PERCENT / total) * value2
        this@DonutChartView.value1Percent = (ONE_HUNDRED_PERCENT / total) * value1

        startAnimation()
    }

    private fun startAnimation() {
        val total = value1 + value2 + value3

        val value3Percent = (ONE_HUNDRED_PERCENT / total) * value3
        val value3Spread = (value3Percent * FULL_CIRCLE_ANGLE_IN_DECIMALS_NUMBER).toFloat()

        val value2Percent = (ONE_HUNDRED_PERCENT / total) * value2
        val value2Spread = (value2Percent * FULL_CIRCLE_ANGLE_IN_DECIMALS_NUMBER).toFloat()

        val value2SpreadExtendedBeyondValue3 = value2Spread + value3Spread

        val animationScale = Settings.Global.getFloat(
            context.contentResolver,
            Settings.Global.ANIMATOR_DURATION_SCALE,
            1.0f
        )

        val (value1Duration, value2Duration, value3Duration) = if (shouldAnimate) {
            Triple(VALUE_1_ANIMATION_DURATION, VALUE_2_ANIMATION_DURATION, VALUE_3_ANIMATION_DURATION)
        } else {
            Triple(NO_ANIMATION_DURATION, NO_ANIMATION_DURATION, NO_ANIMATION_DURATION)
        }

        Value1ArcAnimation().apply {
            setOnAnimationEnd {
                Value2ArcAnimation(value2SpreadExtendedBeyondValue3).apply {
                    setOnAnimationEnd {
                        Value3ArcAnimation(value3Spread).apply {
                            duration = (value3Duration * animationScale).toLong()
                            interpolator = DecelerateInterpolator()
                            startAnimation(this)
                        }
                    }
                    duration = (value2Duration * animationScale).toLong()
                    interpolator = DecelerateInterpolator()
                    startAnimation(this)
                }
            }
            duration = (value1Duration * animationScale).toLong()
            interpolator = DecelerateInterpolator()
            startAnimation(this)
        }
    }

    inner class Value1ArcAnimation : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            value1SweepAngle = FULL_CIRCLE_ANGLE_IN_WHOLE_NUMBER * interpolatedTime
            requestLayout()
        }
    }

    inner class Value2ArcAnimation(private val sweep: Float) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            value2SweepAngle = sweep * -interpolatedTime
            requestLayout()
        }
    }

    inner class Value3ArcAnimation(private val sweep: Float) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            value3SweepAngle = sweep * -interpolatedTime
            requestLayout()
        }
    }

    private fun Animation.setOnAnimationEnd(onEnd: () -> Unit) {
        this.setAnimationListener(object : Animation.AnimationListener {
            @Suppress("EmptyFunctionBlock") override fun onAnimationRepeat(animation: Animation?) {}
            @Suppress("EmptyFunctionBlock") override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) { onEnd() }
        })
    }

    companion object {
        private const val VALUE_1_ANIMATION_DURATION = 1200L
        private const val VALUE_2_ANIMATION_DURATION = 600L
        private const val VALUE_3_ANIMATION_DURATION = 600L
        private const val NO_ANIMATION_DURATION = 0L
        private const val LEFT_OF_CIRCLE = 270f
        private const val ONE_HUNDRED_PERCENT = 100f
        private const val FULL_CIRCLE_ANGLE_IN_WHOLE_NUMBER = 360
        private const val FULL_CIRCLE_ANGLE_IN_DECIMALS_NUMBER = 3.6
        private const val TOTAL_NUM_OF_DASHES = 80f
        private const val INTERVAL_GAP = 0.5f
        private const val PERCENT_FOR_NO_STRIPES = 5f
    }
}
