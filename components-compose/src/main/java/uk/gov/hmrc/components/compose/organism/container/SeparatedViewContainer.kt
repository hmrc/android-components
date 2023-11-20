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
package uk.gov.hmrc.components.compose.organism.container

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.hmrc.components.compose.atom.divider.HmrcDivider

object SeparatedViewContainer {

    @Composable
    operator fun invoke(
        showDivider: DividerMode = DividerMode.SHOW_DIVIDER_NONE,
        vararg views: @Composable () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier) {
            // Show divider in the beginning
            if (showDivider == DividerMode.SHOW_DIVIDER_ALL || showDivider == DividerMode.SHOW_DIVIDER_BEGINNING) {
                HmrcDivider()
            }
            views.forEachIndexed { index, view ->
                view()
                if ((showDivider == DividerMode.SHOW_DIVIDER_ALL || showDivider == DividerMode.SHOW_DIVIDER_MIDDLE) &&
                    index < views.size - 1
                ) {
                    HmrcDivider()
                }
            }
            // Show Divider at the end
            if (showDivider == DividerMode.SHOW_DIVIDER_END || showDivider == DividerMode.SHOW_DIVIDER_ALL) {
                HmrcDivider()
            }
        }
    }

    enum class DividerMode {
        SHOW_DIVIDER_NONE, SHOW_DIVIDER_ALL, SHOW_DIVIDER_BEGINNING, SHOW_DIVIDER_MIDDLE, SHOW_DIVIDER_END
    }
}
