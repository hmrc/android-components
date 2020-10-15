/*
 * Copyright 2020 HM Revenue & Customs
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
package uk.gov.hmrc.components.atom.bullet

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.capture
import uk.gov.hmrc.components.rules.NightMode
import uk.gov.hmrc.components.rules.NightModeRule
import uk.gov.hmrc.components.test.R as testR

class BulletedTextViewTest : ScreenshotTest {

    @Rule
    @JvmField
    val darkModeRule = NightModeRule()

    private lateinit var scenario: ActivityScenario<BulletedTextViewActivity>

    @Before
    fun setup() {
        scenario = launchActivity()
    }

    @Test
    fun testBulletedTextView() {
        captureViews()
    }

    @Test
    @NightMode
    fun testBulletedTextViewNightMode() {
        captureViews(true)
    }

    private fun captureViews(isNightMode: Boolean = false) {
        lateinit var standard: BulletedTextView
        lateinit var bold: BulletedTextView
        lateinit var multiLine: BulletedTextView
        lateinit var heading: BulletedTextView

        scenario.onActivity { activity ->
            standard = activity.findViewById(testR.id.bulleted_text_1)
            bold = activity.findViewById(testR.id.bulleted_text_2)
            multiLine = activity.findViewById(testR.id.bulleted_text_3)
            heading = activity.findViewById(testR.id.bulleted_text_4)
        }

        capture(standard, "standard", isNightMode)
        capture(bold, "bold", isNightMode)
        capture(multiLine, "multi_line", isNightMode)
        capture(heading, "heading", isNightMode)
    }
}
