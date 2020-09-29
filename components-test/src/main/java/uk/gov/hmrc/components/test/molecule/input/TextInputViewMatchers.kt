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
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Description
import org.hamcrest.Matcher
import uk.gov.hmrc.components.molecule.input.TextInputView

object TextInputViewMatchers {

    fun hasHintText(hint: String): Matcher<View> {
        return object : BoundedMatcher<View, TextInputView>(TextInputView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Hint text = $hint")
            }

            override fun matchesSafely(item: TextInputView?): Boolean {
                return item?.getEditText()?.hint == hint
            }
        }
    }

    fun hasError(@StringRes resId: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextInputView>(TextInputView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText(
                        "Error text = ${InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)}"
                )
            }

            override fun matchesSafely(item: TextInputView?): Boolean {
                return item?.getError() == InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
            }
        }
    }

    fun hasError(error: String): Matcher<View> {
        return object : BoundedMatcher<View, TextInputView>(TextInputView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Error text = $error")
            }

            override fun matchesSafely(item: TextInputView?): Boolean {
                return item?.getError() == error
            }
        }
    }
}
