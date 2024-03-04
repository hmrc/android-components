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
package uk.gov.hmrc.components.compose.organism.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.extensions.enableTalkBackMergeAccessibility
import uk.gov.hmrc.components.compose.ui.theme.HmrcAlwaysBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlue
import uk.gov.hmrc.components.compose.ui.theme.HmrcRed
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhite
import uk.gov.hmrc.components.compose.ui.theme.HmrcYellow


enum class Type(val headlineBackgroundColor: Color, val headlineTint: Color, val iconColor: Color) {
    WARNING(HmrcYellow, HmrcAlwaysBlack, HmrcYellow),
    INFO(HmrcBlue, HmrcWhite, HmrcBlue),
    URGENT(HmrcRed, HmrcWhite, HmrcRed),
    NOTICE(HmrcBlack, HmrcWhite, HmrcBlack)
}

@Composable
fun InformationMessageCardView(
    modifier: Modifier = Modifier,
    headline: String,
    headlineContentDescription: String = "",
    text: String?,
    headlineIcon: Int,
    type: Type,
    headlineButtons: @Composable (ColumnScope.() -> Unit)? = null
) {
    val textColor: Color by rememberSaveable { mutableStateOf(type.headlineTint) }
    val iconColor: Color by rememberSaveable { mutableStateOf(type.iconColor) }
    val backgroundColor: Color by rememberSaveable { mutableStateOf(type.headlineBackgroundColor) }

    HmrcCardView(modifier = modifier.background(backgroundColor)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .enableTalkBackMergeAccessibility()
        ) {
            Image(
                modifier = Modifier.size(HmrcTheme.dimensions.hmrcIconSize36),
                painter = painterResource(id = headlineIcon),
                contentDescription = "",
                colorFilter = ColorFilter.tint(iconColor)
            )
            Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing8))

            Text(
                text = headline,
                modifier = Modifier
                    .weight(1f)
                    .heightIn(HmrcTheme.dimensions.hmrcIconSize36)
                    .wrapContentSize(align = Alignment.CenterStart)
                    .semantics {
                        this.contentDescription = headlineContentDescription
                    },
                style = HmrcTheme.typography.h6,
                color = textColor
            )
        }
        if (text != null) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = text,
                    modifier = Modifier,
                    style = HmrcTheme.typography.h4,
                    color = textColor
                )
            }
        }
        if (headlineButtons != null) {
            headlineButtons()
        }
    }
}
