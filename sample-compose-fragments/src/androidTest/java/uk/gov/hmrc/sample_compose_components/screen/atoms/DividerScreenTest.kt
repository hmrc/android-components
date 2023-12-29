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

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import org.junit.Rule
import org.junit.Test
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.atoms.DividerScreen

class DividerScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun dividerScreenTest() {
        rule.setContent {
            HmrcTheme {
                DividerScreen()
            }
        }

        rule.apply {
            onAllNodesWithText("Content").assertCountEquals(4)
            // Adding comments here from findings:
            // Cannot test the divider exist using Compose Testing,
            // as this is more UI testing rather then Compose semantic testing.
        }
    }
}
