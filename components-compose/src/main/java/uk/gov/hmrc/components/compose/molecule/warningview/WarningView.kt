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
package uk.gov.hmrc.components.compose.molecule.warningview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object WarningView {

    const val NO_ICON: Int = -1

    @Composable
    operator fun invoke(
        text: String,
        textColor: Color = HmrcTheme.colors.hmrcBlack,
        icon: Int = R.drawable.components_ic_warning,
        iconTintColor: Color = HmrcTheme.colors.hmrcBlack,
        backgroundColor: Color = HmrcTheme.colors.hmrcWhiteBackground,
        padding: Dp = HmrcTheme.dimensions.hmrcSpacing8
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
            ) {
                if (icon != NO_ICON) {
                    Image(
                        modifier = Modifier.size(HmrcTheme.dimensions.hmrcIconSize36),
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(iconTintColor)
                    )
                    Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing8))
                }
                Text(
                    text = text,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(HmrcTheme.dimensions.hmrcIconSize36)
                        .wrapContentSize(align = Alignment.CenterStart),
                    style = HmrcTheme.typography.h6,
                    color = textColor
                )
            }
        }
    }
}
