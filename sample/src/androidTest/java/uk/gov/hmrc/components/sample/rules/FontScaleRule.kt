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
package uk.gov.hmrc.components.sample.rules

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.provider.Settings
import androidx.core.net.toUri
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class FontScaleRule : TestRule {

    override fun apply(
        base: Statement,
        description: Description
    ) = object : Statement() {
        override fun evaluate() {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val targetContext = instrumentation.targetContext

            if (!Settings.System.canWrite(targetContext)) {
                // we need to enable access to Settings first
                targetContext.startActivity(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
                    data = "package:${targetContext.packageName}".toUri()
                    flags = FLAG_ACTIVITY_NEW_TASK
                })

                UiDevice.getInstance(instrumentation)
                    .run {
                        findObject(UiSelector().checkable(true)).click()
                    }
            }

            val contentResolver = targetContext.contentResolver

            val currentScale = Settings.System.getFloat(
                contentResolver,
                Settings.System.FONT_SCALE,
                1.0F
            )
            val newScale = description.getAnnotation(FontScale::class.java)?.scale ?: 1.0F

            if (currentScale != newScale) {
                Settings.System.putFloat(
                    contentResolver,
                    Settings.System.FONT_SCALE,
                    newScale
                )
            }

            base.evaluate()
        }
    }
}
