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
package uk.gov.hmrc.components.screenshots.atom.button

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.karumi.shot.ScreenshotTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.atom.button.IconButton
import uk.gov.hmrc.components.screenshots.R
import uk.gov.hmrc.components.screenshots.capture
import uk.gov.hmrc.components.screenshots.rules.FontScale
import uk.gov.hmrc.components.screenshots.rules.FontScaleRule
import uk.gov.hmrc.components.screenshots.rules.NightMode
import uk.gov.hmrc.components.screenshots.rules.NightModeRule

class IconButtonTest : ScreenshotTest {

    @get:Rule
    val nightModeRule = NightModeRule()
    @get:Rule
    val fontScaleRule = FontScaleRule()

    private lateinit var scenario: ActivityScenario<IconButtonActivity>
    private lateinit var rootView: View

    @Before
    fun setup() {
        scenario = launchActivity()
        scenario.onActivity { rootView = it.findViewById(R.id.root) }
    }

    @Test
    fun testIconButtonClickListener() {
        var wasClicked = false
        scenario.onActivity { activity ->
            activity.findViewById<IconButton>(R.id.icon_button_warning).setOnClickListener {
                wasClicked = true
            }
        }

        onView(withId(R.id.icon_button_warning)).perform(click())

        assertTrue(wasClicked)
    }

    @Test
    fun iconButtonScreenshots() {
        capture(rootView)
    }

    @Test
    @NightMode
    fun iconButtonNightModeScreenshots() {
        capture(rootView, isNightMode = true)
    }

    @Test
    @FontScale(2f)
    fun iconButtonFontScaleScreenshots() {
        capture(rootView, isFontScaled = true)
    }
}
