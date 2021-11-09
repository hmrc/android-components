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
package uk.gov.hmrc.components.sample.atoms

import org.junit.Test
import uk.gov.hmrc.components.sample.BaseActivityTest
import uk.gov.hmrc.components.sample.rules.FontScale
import uk.gov.hmrc.components.sample.rules.NightMode

class DividerTest : BaseActivityTest() {

    @Test
    fun screenshots() {
        tapTab(TAB_IDENTIFIER)
        screenshots(IDENTIFIER)
    }

    @Test
    @NightMode
    fun nightModeScreenshots() {
        tapTab(TAB_IDENTIFIER)
        nightModeScreenshots(IDENTIFIER)
    }

    @Test
    @FontScale(scale = 2f)
    fun fontScaleScreenshots() {
        tapTab(TAB_IDENTIFIER)
        fontScaleScreenshots(IDENTIFIER)
    }

    companion object {
        const val IDENTIFIER = "Divider"
        const val TAB_IDENTIFIER = "Atoms"
    }
}
