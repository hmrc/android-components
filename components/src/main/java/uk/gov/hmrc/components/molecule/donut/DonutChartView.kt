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
import android.graphics.Paint
import android.graphics.RectF
import android.provider.Settings
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
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

    private val color1Paint: Paint
    private val color2Paint: Paint

    private val donutWidth = context.resources.getDimension(R.dimen.donut_width)
    private var rect: RectF? = null
    private val startAngle: Float = LEFT_OF_CIRCLE
    private var value1SweepAngle: Float = 0f
    private var value2SweepAngle: Float = 0f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DonutChartView,
            0,
            0
        ).apply {
            try {
                color1 = getColor(
                    R.styleable.DonutChartView_color1,
                    ContextCompat.getColor(context, R.color.hmrc_donut_chart_color_1)
                )
                color2 = getColor(
                    R.styleable.DonutChartView_color2,
                    ContextCompat.getColor(context, R.color.hmrc_donut_chart_color_2)
                )
            } finally {
                recycle()
            }

            color1Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.STROKE
                strokeWidth = donutWidth
                color = color1
            }

            color2Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.STROKE
                strokeWidth = donutWidth
                color = color2
            }
        }
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
        canvas?.apply {
            drawArc(
                rect!!,
                startAngle,
                value1SweepAngle,
                false,
                color1Paint
            )

            drawArc(
                rect!!,
                startAngle,
                value2SweepAngle,
                false,
                color2Paint
            )
        }
    }

    fun startAnimation(value1: Float, value2: Float, shouldAnimate: Boolean) {
        val value2Percent = (ONE_HUNDRED_PERCENT / (value1 + value2)) * value2
        val value2Spread = value2Percent * FULL_CIRCLE_ANGLE_IN_DECIMALS_NUMBER
        val animationScale = Settings.Global.getFloat(
            context.contentResolver,
            Settings.Global.ANIMATOR_DURATION_SCALE,
            1.0f
        )

        val (value1Duration, value2Duration) = if (shouldAnimate) {
            Pair(VALUE_1_ANIMATION_DURATION, VALUE_2_ANIMATION_DURATION)
        } else {
            Pair(NO_ANIMATION_DURATION, NO_ANIMATION_DURATION)
        }

        Value1ArcAnimation().apply {
            setAnimationListener(object : Animation.AnimationListener {
                @Suppress("EmptyFunctionBlock")
                override fun onAnimationRepeat(animation: Animation?) {}

                @Suppress("EmptyFunctionBlock")
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    Value2ArcAnimation(value2Spread.toFloat()).apply {
                        duration = (value2Duration * animationScale).toLong()
                        startAnimation(this)
                    }
                }
            })
            duration = (value1Duration * animationScale).toLong()
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

    companion object {
        private const val VALUE_1_ANIMATION_DURATION = 600L
        private const val VALUE_2_ANIMATION_DURATION = 300L
        private const val NO_ANIMATION_DURATION = 0L
        private const val LEFT_OF_CIRCLE = 270f
        private const val ONE_HUNDRED_PERCENT = 100f
        private const val FULL_CIRCLE_ANGLE_IN_WHOLE_NUMBER = 360
        private const val FULL_CIRCLE_ANGLE_IN_DECIMALS_NUMBER = 3.6
    }
}
