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
package uk.gov.hmrc.components.screenshots.atom.bullet

import android.view.View
import android.widget.LinearLayout
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.atom.bullet.BulletedTextView
import uk.gov.hmrc.components.screenshots.R
import uk.gov.hmrc.components.screenshots.capture
import uk.gov.hmrc.components.screenshots.rules.FontScale
import uk.gov.hmrc.components.screenshots.rules.FontScaleRule
import uk.gov.hmrc.components.screenshots.rules.NightMode
import uk.gov.hmrc.components.screenshots.rules.NightModeRule

class BulletedTextViewTest : ScreenshotTest {

    @get:Rule
    val darkModeRule = NightModeRule()
    @get:Rule
    val fontScaleRule = FontScaleRule()

    private lateinit var scenario: ActivityScenario<BulletedTextViewActivity>
    private lateinit var rootView: View

    @Before
    fun setup() {
        scenario = launchActivity()
        scenario.onActivity { rootView = it.findViewById(R.id.root) }
    }

    @Test
    fun bulletedTextViewScreenshots() {
        capture(rootView)
    }

    @Test
    @NightMode
    fun bulletedTextViewNightModeScreenshots() {
        capture(rootView, isNightMode = true)
    }

    @Test
    @FontScale(scale = 2f)
    fun bulletedTextViewFontScaleScreenshots() {
        capture(rootView, isFontScaled = true)
    }

}
