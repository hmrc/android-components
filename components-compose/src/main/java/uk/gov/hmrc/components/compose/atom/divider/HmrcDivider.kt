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
package uk.gov.hmrc.components.compose.atom.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object HmrcDivider {
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        thickness: Dp = HmrcTheme.dimensions.hmrcDividerHeight,
        backgroundColor: Color = HmrcTheme.colors.hmrcDivider
    ) {
        Divider(
            modifier = modifier.apply {
                height(thickness)
                background(color = backgroundColor)
            }
        )
    }
}
