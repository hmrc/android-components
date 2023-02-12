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
import uk.gov.hmrc.components.compose.atom.heading.Heading3
import uk.gov.hmrc.components.compose.atom.heading.Heading4
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcTypography
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16

@Composable
fun TextScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(hmrc_spacing_16).verticalScroll(rememberScrollState())
    ) {
        Heading3(text = "Heading3 text")
        Text(
            text = "H3 text", style = HmrcTypography.typography.h3, modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Heading4(text = "Heading4 text", modifier = Modifier.padding(top = hmrc_spacing_16))
        Text(
            text = "H4 text", style = HmrcTypography.typography.h4,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Heading5(text = "Heading5 text", modifier = Modifier.padding(top = hmrc_spacing_16))
        Text(
            text = "H5 text", style = HmrcTypography.typography.h5,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = "Bold text", style = HmrcTypography.typography.h6,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = "Body text", style = HmrcTypography.typography.body,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = "Info text", style = HmrcTypography.typography.info,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = "Error text", style = HmrcTypography.typography.errorText,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
    }
}
