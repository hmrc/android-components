/*
 * Copyright 2019 HM Revenue & Customs
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
package uk.gov.hmrc.components.organism.expandable

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class LinearRevealAnimation(
    private val revealedContent: View,
    private val from: Int,
    private val to: Int
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val newHeight = from + (to - from) * interpolatedTime
        revealedContent.layoutParams.height = newHeight.toInt()

        revealedContent.requestLayout()
    }

    override fun willChangeBounds(): Boolean {
        return true
    }

    fun withDuration(duration: Long): LinearRevealAnimation {
        this.duration = duration
        return this
    }

    fun start(view: View, completeCallback: (() -> Unit)? = null) {
        revealedContent.visibility = View.VISIBLE
        this.setAnimationListener(object : AnimationListener {

            @Suppress("EmptyFunctionBlock")
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationStart(animation: Animation?) {
                revealedContent.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                completeCallback?.let { it.invoke() }
            }
        })
        view.startAnimation(this)
    }
}
