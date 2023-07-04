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
package uk.gov.hmrc.components.compose.molecule.statusview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.atom.button.SecondaryButton
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object StatusView {

    const val NO_ICON: Int = -1

    enum class BodyAlignment(val alignment: TextAlign) {
        CENTER(TextAlign.Center),
        START(TextAlign.Start),
    }

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        title: String,
        body: String = "",
        bodyContentDesc: String = "",
        bodyAlignment: BodyAlignment = BodyAlignment.CENTER,
        textColor: Color = HmrcTheme.colors.hmrcBlack,
        icon: Int,
        iconTint: Color = HmrcTheme.colors.hmrcBlack,
        primaryButtonText: String = "",
        secondaryButtonText: String = "",
        infoText: String = "",
        onPrimaryButtonClickedListener: () -> Unit = {},
        onSecondaryButtonClickedListener: () -> Unit = {}
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //region Icon
            if (icon != NO_ICON) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(HmrcTheme.dimensions.hmrcIconSize100)
                        .width(HmrcTheme.dimensions.hmrcIconSize100),
                    colorFilter = ColorFilter.tint(iconTint),
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing24))
            }
            //endregion

            //region Title
            Text(
                text = title,
                style = HmrcTheme.typography.h5.copy(color = textColor),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
            //endregion

            //region Body
            if (body.isNotEmpty()) {
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing24))
                Text(
                    text = body,
                    style = HmrcTheme.typography.body.copy(color = textColor),
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            contentDescription = bodyContentDesc.ifEmpty { body }
                        },
                    textAlign = bodyAlignment.alignment
                )
            }
            //endregion

            //region Primary Button
            if (primaryButtonText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
                PrimaryButton(text = primaryButtonText) {
                    onPrimaryButtonClickedListener()
                }
            }
            //endregion

            //region Secondary Button
            if (secondaryButtonText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
                SecondaryButton(text = secondaryButtonText, onClick = { onSecondaryButtonClickedListener() })
            }
            //endregion

            //region Info Text
            if (infoText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
                Text(
                    text = infoText,
                    style = HmrcTheme.typography.info.copy(fontSize = 12.sp),
                    modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
                )
            }
            //endregion
        }
    }
}
