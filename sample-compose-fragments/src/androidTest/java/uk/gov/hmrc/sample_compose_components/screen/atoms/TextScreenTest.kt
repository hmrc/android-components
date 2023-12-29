/*
 * Copyright 2023 HM Revenue & Customs
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
package uk.gov.hmrc.sample_compose_components.screen.atoms

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.atoms.TextScreen

class TextScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun textScreenAllTextsTest() {
        rule.setContent {
            HmrcTheme {
                TextScreen()
            }
        }

        rule.apply {
            onNodeWithText("Heading3 text").assertIsDisplayed()
            onNodeWithText("H3 text").assertIsDisplayed()
            onNodeWithText("Heading4 text").assertIsDisplayed()
            onNodeWithText("H4 text").assertIsDisplayed()
            onNodeWithText("Heading5 text").assertIsDisplayed()
            onNodeWithText("H5 text").assertIsDisplayed()
            onNodeWithText("Bold text").assertIsDisplayed()
            onNodeWithText("Body text").assertIsDisplayed()
            onNodeWithText("Info text").assertIsDisplayed()
            onNodeWithText("Error text").assertIsDisplayed()
            onNodeWithText("Bulleted Text without newline characters that extends over multiple lines in order to demonstrate indenting. Bulleted Text without newline characters that extends over multiple lines in order to demonstrate indenting.").assertIsDisplayed()
            onNodeWithText("Bulleted Text with newline characters\nthat extends over multiple lines\nin order to demonstrate indenting.").assertIsDisplayed()
        }
    }
}
