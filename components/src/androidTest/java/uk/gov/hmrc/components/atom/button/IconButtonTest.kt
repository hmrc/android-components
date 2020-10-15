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
package uk.gov.hmrc.components.atom.button

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
import uk.gov.hmrc.components.capture
import uk.gov.hmrc.components.rules.NightMode
import uk.gov.hmrc.components.rules.NightModeRule
import uk.gov.hmrc.components.test.R as testR

class IconButtonTest : ScreenshotTest {

    @Rule
    @JvmField
    val darkModeRule = NightModeRule()

    private lateinit var scenario: ActivityScenario<IconButtonActivity>

    @Before
    fun setup() {
        scenario = launchActivity()
    }

    @Test
    fun testIconButtonClickListener() {
        var wasClicked = false
        scenario.onActivity { activity ->
           activity.findViewById<IconButton>(testR.id.icon_button_warning).setOnClickListener {
               wasClicked = true
           }
        }

        onView(withId(testR.id.icon_button_warning)).perform(click())

        assertTrue(wasClicked)
    }

    @Test
    fun iconButtonScreenshots() {
        captureViews()
    }

    @Test
    @NightMode
    fun iconButtonNightModeScreenshots() {
        captureViews(true)
    }

    private fun captureViews(isNightMode: Boolean = false) {
        lateinit var warning: IconButton

        scenario.onActivity { activity ->
            warning = activity.findViewById(testR.id.icon_button_warning)
        }

        capture(warning, "warning", isNightMode)
    }
}