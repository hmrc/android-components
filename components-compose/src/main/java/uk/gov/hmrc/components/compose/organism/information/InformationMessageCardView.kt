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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.button.HmrcButton
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.extensions.enableTalkBackMergeAccessibility
import uk.gov.hmrc.components.compose.ui.theme.HmrcAlwaysBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlue
import uk.gov.hmrc.components.compose.ui.theme.HmrcRed
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhite
import uk.gov.hmrc.components.compose.ui.theme.HmrcYellow

enum class MessageType(val backgroundColor: Color, val headlineTint: Color, val iconColor: Color, val icon: Int) {
    WARNING(HmrcYellow, HmrcAlwaysBlack, HmrcAlwaysBlack, R.drawable.components_ic_warning),
    INFO(HmrcBlue, HmrcWhite, HmrcWhite, R.drawable.ic_info),
    URGENT(HmrcRed, HmrcWhite, HmrcWhite, R.drawable.ic_info),
    NOTICE(HmrcBlack, HmrcWhite, HmrcWhite, R.drawable.components_ic_warning)
}

@Composable
fun InformationMessageCardView(
    modifier: Modifier = Modifier,
    headline: String,
    headlineContentDescription: String = "",
    text: String? = null,
    messageType: MessageType,
    buttons: List<InformationMessageButton> = emptyList()
) {
    HmrcCardView(modifier = modifier, customBackgroundColor = messageType.backgroundColor) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .enableTalkBackMergeAccessibility()
                .padding(HmrcTheme.dimensions.hmrcSpacing16),
        ) {
            Image(
                modifier = Modifier.size(HmrcTheme.dimensions.hmrcIconSize36),
                painter = painterResource(id = messageType.icon),
                contentDescription = "",
                colorFilter = ColorFilter.tint(messageType.iconColor)
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
                color = messageType.headlineTint
            )
        }
        if (text != null) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = HmrcTheme.dimensions.hmrcSpacing16,
                        end = HmrcTheme.dimensions.hmrcSpacing16,
                        bottom = HmrcTheme.dimensions.hmrcSpacing16
                    )
            ) {
                Text(
                    text = text,
                    modifier = Modifier,
                    style = HmrcTheme.typography.body,
                    color = messageType.headlineTint
                )
            }
        }
        if (buttons.isNotEmpty()) {
            buttons.forEach {
                it.invoke()
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
        }
    }
}

class InformationMessageButton(
    val text: String,
    val messageType: MessageType,
    val buttonType: ButtonType = ButtonType.ACTION,
    val onClick: () -> Unit
) {
    @Composable
    operator fun invoke() {
        val (colors, border) = when (buttonType) {
            ButtonType.ACTION -> {
                Pair(
                    ButtonDefaults.buttonColors(
                        containerColor = HmrcTheme.colors.hmrcWhite, contentColor = HmrcTheme.colors.hmrcBlue
                    ),
                    null
                )
            }
            ButtonType.OUTLINE -> {
                Pair(
                    ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent, contentColor = messageType.headlineTint
                    ),
                    BorderStroke(width = 1.0.dp, color = messageType.headlineTint,)
                )
            }
        }
        CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
            HmrcButton(
                text = text,
                modifier = Modifier.padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16),
                contentPadding = PaddingValues(all = HmrcTheme.dimensions.hmrcSpacing16),
                buttonColors = colors,
                border = border,
                onClick = onClick,
            )
        }
    }
    companion object {
        enum class ButtonType {
            ACTION, OUTLINE
        }
    }
}
