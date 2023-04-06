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
package uk.gov.hmrc.components.compose.atom.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors
import uk.gov.hmrc.components.compose.ui.theme.hmrc_button_size_48
import uk.gov.hmrc.components.compose.ui.theme.hmrc_icon_size_24
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16

@Composable
fun HmrcButton(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    enabled: Boolean,
    shape: Shape = RectangleShape,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit = {},
) {
    Button(
        modifier = modifier.heightIn(min = hmrc_button_size_48).fillMaxWidth(),
        colors = buttonColors,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        content()
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = textAlign,
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
    onClick: () -> Unit,
) {
    val primaryButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = colors.hmrcGreen1, contentColor = colors.hmrcWhite
    )
    val disabledPrimaryButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = colors.hmrcGrey2, disabledContentColor = colors.hmrcGrey1
    )
    HmrcButton(
        text = text,
        modifier = modifier,
        enabled = enabled,
        textAlign = textAlign,
        buttonColors = if (enabled) primaryButtonColors else disabledPrimaryButtonColors,
        onClick = onClick
    )
}

@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent, contentColor = colors.hmrcBlue
    ),
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit = {},
) {
    CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
        HmrcButton(
            text = text,
            modifier = modifier,
            contentPadding = PaddingValues(all = hmrc_spacing_16),
            enabled = enabled,
            textAlign = textAlign,
            buttonColors = buttonColors,
            onClick = onClick,
        ) {
            content()
        }
    }
}

@Composable
fun IconButton(
    text: String,
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    onClick: () -> Unit,
) {
    SecondaryButton(text = text, modifier = Modifier, textAlign = textAlign, onClick = onClick) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = modifier.size(hmrc_icon_size_24)
        )
        Spacer(modifier = modifier.width(hmrc_spacing_16))
    }
}
