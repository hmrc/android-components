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
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.karumi.shot.ScreenshotTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import uk.gov.hmrc.components.sample.base.ComponentAdapter
import uk.gov.hmrc.components.sample.rules.FontScaleRule
import uk.gov.hmrc.components.sample.rules.NightModeRule

open class BaseActivityTest : ScreenshotTest {

    @get:Rule
    val nightModeRule = NightModeRule()

    @get:Rule
    val fontScaleRule = FontScaleRule()

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var rootView: View

    @Before
    fun setup() {
        scenario = launchActivity()
        scenario.onActivity { rootView = it.findViewById(R.id.container) }
    }

    fun screenshots(identifier: String) {
        tap(identifier)
        capture(
            R.id.content_layout
        )
    }

    fun nightModeScreenshots(identifier: String) {
        tap(identifier)
        capture(
            R.id.content_layout,
            isNightMode = true
        )
    }

    fun fontScaleScreenshots(identifier: String) {
        tap(identifier)
        capture(
            R.id.content_layout,
            isFontScaled = true
        )
    }

    fun screenshots(position: Int) {
        tap(position)
        capture(
            R.id.content_layout
        )
    }

    fun nightModeScreenshots(position: Int) {
        tap(position)
        capture(
            R.id.content_layout,
            isNightMode = true
        )
    }

    fun fontScaleScreenshots(position: Int) {
        tap(position)
        capture(
            R.id.content_layout,
            isFontScaled = true
        )
    }

    private fun tap(identifier: String) {
        onView(
            withText(identifier)
        ).perform(click())
    }

    private fun tap(position: Int) {
        onView(withId(R.id.component_list))
            .perform(
                actionOnItemAtPosition<ComponentAdapter.ViewHolder>(
                    position,
                    click()
                )
            )
    }

    open fun tapTab(identifier: String) {
        onView(
            allOf(
                withText(identifier),
                isDescendantOfA(withId(R.id.bottom_navigation)),
                isDisplayed()
            )
        ).perform(click())
    }
}
