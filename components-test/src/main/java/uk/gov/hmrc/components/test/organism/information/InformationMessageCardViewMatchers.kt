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
package uk.gov.hmrc.components.test.organism.information

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import kotlin.reflect.KClass
import org.hamcrest.Description
import org.hamcrest.Matcher
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.atom.button.Button
import uk.gov.hmrc.components.atom.button.SecondaryButton
import uk.gov.hmrc.components.molecule.warning.WarningView
import uk.gov.hmrc.components.organism.information.InformationMessageCardView

object InformationMessageCardViewMatchers {

    fun hasType(type: InformationMessageCardView.Type): Matcher<View> {
        return object : BoundedMatcher<View, InformationMessageCardView>(InformationMessageCardView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Type = $type")
            }

            override fun matchesSafely(item: InformationMessageCardView?): Boolean {
                return item?.type == type
            }
        }
    }

    fun hasHeadline(headline: String): Matcher<View> {
        return object : BoundedMatcher<View, InformationMessageCardView>(InformationMessageCardView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Headline = $headline")
            }

            override fun matchesSafely(item: InformationMessageCardView?): Boolean {
                return item?.findViewById<WarningView>(R.id.warning_view)?.getText() == headline
            }
        }
    }

    fun hasTitle(title: String): Matcher<View> {
        return object : BoundedMatcher<View, InformationMessageCardView>(InformationMessageCardView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Title = $title")
            }

            override fun matchesSafely(item: InformationMessageCardView?): Boolean {
                return item?.findViewById<TextView>(R.id.content_title)?.text == title
            }
        }
    }

    fun hasBody(body: String): Matcher<View> {
        return object : BoundedMatcher<View, InformationMessageCardView>(InformationMessageCardView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Body = $body")
            }

            override fun matchesSafely(item: InformationMessageCardView?): Boolean {
                return item?.findViewById<TextView>(R.id.content_body)?.text == body
            }
        }
    }

    fun hasContentButton(position: Int, text: String, kClass: KClass<*>): Matcher<View> {
        return object : BoundedMatcher<View, InformationMessageCardView>(InformationMessageCardView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Content button = $position $text")
            }

            override fun matchesSafely(item: InformationMessageCardView?): Boolean {
                val button = (item?.findViewById<LinearLayout>(R.id.buttons_container)?.getChildAt(position) as? Button)
                return button?.text == text && button::class == kClass
            }
        }
    }

    fun hasHeadlineButton(position: Int, text: String): Matcher<View> {
        return object : BoundedMatcher<View, InformationMessageCardView>(InformationMessageCardView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Headline button = $position $text")
            }

            override fun matchesSafely(item: InformationMessageCardView?): Boolean {
                val button = (item?.findViewById<LinearLayout>(R.id.headline_buttons_container)
                    ?.getChildAt(position) as? SecondaryButton)
                return button?.text == text
            }
        }
    }
}
