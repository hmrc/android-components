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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.heading.Heading3
import uk.gov.hmrc.components.compose.atom.heading.Heading4
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.extensions.HtmlText
import uk.gov.hmrc.components.compose.ui.theme.CustomHmrcTypography
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.sample_compose_components.R

@Composable
fun TextScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(hmrc_spacing_16).verticalScroll(rememberScrollState())
    ) {
        Heading3(text = "Heading3 text")
        Text(
            text = "H3 text", style = CustomHmrcTypography.h3,
            modifier = Modifier.padding(top = hmrc_spacing_16), color = HmrcTheme.colors.hmrcBlack
        )
        Heading4(text = "Heading4 text", modifier = Modifier.padding(top = hmrc_spacing_16))
        Text(
            text = "H4 text", style = CustomHmrcTypography.h4,
            modifier = Modifier.padding(top = hmrc_spacing_16), color = HmrcTheme.colors.hmrcBlack
        )
        Heading5(text = "Heading5 text", modifier = Modifier.padding(top = hmrc_spacing_16))
        Text(
            text = "H5 text", style = CustomHmrcTypography.h5,
            modifier = Modifier.padding(top = hmrc_spacing_16), color = HmrcTheme.colors.hmrcBlack
        )
        Text(
            text = "Bold text", style = CustomHmrcTypography.h6,
            modifier = Modifier.padding(top = hmrc_spacing_16), color = HmrcTheme.colors.hmrcBlack
        )
        Text(
            text = "Body text", style = CustomHmrcTypography.body,
            modifier = Modifier.padding(top = hmrc_spacing_16), color = HmrcTheme.colors.hmrcBlack
        )
        Text(
            text = "Info text", style = CustomHmrcTypography.info,
            modifier = Modifier.padding(top = hmrc_spacing_16), color = HmrcTheme.colors.hmrcGrey1
        )
        HtmlText(
            text = stringResource(id = R.string.text_link),
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = "Error text", color = HmrcTheme.colors.hmrcRed,
            style = CustomHmrcTypography.body, modifier = Modifier.padding(top = hmrc_spacing_16)
        )
    }
}
