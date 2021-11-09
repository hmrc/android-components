/*
 * Copyright 2021 HM Revenue & Customs
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
package uk.gov.hmrc.components.sample

import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.facebook.testing.screenshot.Screenshot
import com.facebook.testing.screenshot.ViewHelpers
import com.facebook.testing.screenshot.internal.TestNameDetector
import com.karumi.shot.ScreenshotTest

fun ScreenshotTest.capture(
    @IdRes viewId: Int,
    isNightMode: Boolean = false,
    isFontScaled: Boolean = false
) {
    val name = when {
        isNightMode -> "night"
        isFontScaled -> "scaled"
        else -> "day"
    }

    var viewToCapture: View? = null

    InstrumentationRegistry.getInstrumentation()
        .runOnMainSync {
            val activity = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED)
                .first()
            viewToCapture = activity.findViewById(viewId)
        }

    requireNotNull(viewToCapture)

    viewToCapture?.let {
        val height = if (it is RecyclerView) it.contentHeight else it.height
        if (height * it.width > Screenshot.getMaxPixels()) {
            disableFlakyComponentsAndWaitForIdle(it)
            runOnUi {
                ViewHelpers.setupView(it)
                    .setExactHeightPx(height)
                    .setExactWidthPx(it.width)
                    .layout()
            }
            val snapshotName = "${TestNameDetector.getTestClass()}_$name"
            Screenshot
                .snap(it)
                .setMaxPixels(-1)
                .setIncludeAccessibilityInfo(false)
                .setName(snapshotName)
                .record()
        } else {
            compareScreenshot(
                it,
                height,
                it.width,
                name
            )
        }
    }
}

private val RecyclerView.contentHeight: Int
    get() {
        val adapter = adapter!!
        val size = adapter.itemCount
        var contentHeight = 0
        val recyclerWidth = width
        InstrumentationRegistry.getInstrumentation()
            .runOnMainSync {
                for (i in 0 until size) {
                    val holder = adapter.createViewHolder(
                        this,
                        adapter.getItemViewType(i)
                    )
                    adapter.onBindViewHolder(
                        holder,
                        i
                    )
                    holder.itemView.run {
                        measure(
                            View.MeasureSpec.makeMeasureSpec(
                                recyclerWidth,
                                View.MeasureSpec.EXACTLY
                            ),
                            View.MeasureSpec.makeMeasureSpec(
                                0,
                                View.MeasureSpec.UNSPECIFIED
                            )
                        )
                        layout(
                            0,
                            0,
                            measuredWidth,
                            measuredHeight
                        )
                    }
                    contentHeight += holder.itemView.measuredHeight + holder.itemView.marginTop + holder.itemView.marginBottom
                }
            }

        return contentHeight
    }
