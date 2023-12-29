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

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.atoms.ButtonScreen

class ButtonScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun buttonScreenAllButtonsText() {
        rule.setContent {
            HmrcTheme {
                ButtonScreen()
            }
        }

        rule.apply {
            onNodeWithText("Primary button").apply {
                assertIsDisplayed()
                assertExists()
                assertHasClickAction()
                hasClickAction()
            }
            onNodeWithText("Disabled primary button").apply {
                assertIsDisplayed()
                assertExists()
                hasNoClickAction()
            }
            onNodeWithText("Secondary button").apply {
                assertIsDisplayed()
                assertExists()
                assertHasClickAction()
                hasClickAction()
            }
            onNodeWithText("Icon button").apply {
                assertIsDisplayed()
                assertExists()
                assertHasClickAction()
                hasClickAction()
            }
        }

        // Adding comments here from findings:
        // Cannot test the button colour, or text alignment,
        // as this is more UI testing rather then Compose semantic testing.
    }
}
