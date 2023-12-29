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
package uk.gov.hmrc.sample_compose_components.screen.molecules

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules.CurrencyInputViewScreen

class CurrencyInputViewScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun currencyInputViewScreenValidInputTest() {
        rule.setContent {
            HmrcTheme {
                CurrencyInputViewScreen()
            }
        }

        rule.apply {
            // Adding comments here from findings:
            // To use `onNodeWithTag`, need to add a `Modifier.testTag("tagName")` on the Composable.
            // This is almost like adding an ID for the Composable element.
            onNodeWithTag("CurrencyInputViewPayAmount").performTextInput("1234")

            // As the £ sign is inserted as a Composable as the prefix, assertion needs to
            // be done this way instead of the normal "assertTextEquals()".
            onNode(hasText("£") and hasText("1234")).assertExists()
        }
    }

    @Test
    fun currencyInputViewScreenEmptyInputTest() {
        rule.setContent {
            HmrcTheme {
                CurrencyInputViewScreen()
            }
        }

        rule.apply {
            onNodeWithTag("CurrencyInputViewPayAmount").apply {
                performTextClearance()
                performTextInput("\n") // Unsure how to actually press "enter", newline seems to have done the trick.
            }
            onNodeWithTag("CurrencyInputViewPayAmountPoundOne").apply {
                performTextClearance()
                performTextInput("\n") // Unsure how to actually press "enter", newline seems to have done the trick.
            }
            onNodeWithTag("CurrencyInputViewPayAmountPoundTwo").apply {
                performTextClearance()
                performTextInput("\n") // Unsure how to actually press "enter", newline seems to have done the trick.
            }
            onAllNodesWithText("Enter an amount").assertCountEquals(3)
        }
    }
}
