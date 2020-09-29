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
package uk.gov.hmrc.components.test.molecule.input

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import uk.gov.hmrc.components.molecule.input.TextInputView

object TextInputViewActions {

    fun typeText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Failed to type text: $text"
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        isAssignableFrom(TextInputView::class.java)
                )
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as TextInputView).setText(text)
            }
        }
    }
}
