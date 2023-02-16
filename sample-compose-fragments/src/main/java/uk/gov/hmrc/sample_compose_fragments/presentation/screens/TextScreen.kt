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
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.sample_compose_components.R

@Composable
fun TextScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(start = hmrc_spacing_16, end = hmrc_spacing_16).verticalScroll(rememberScrollState())
    ) {
        Heading3(text = stringResource(id = R.string.text_heading3))
        Text(
            text = stringResource(id = R.string.text_h3), style = typography.h3, modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Heading4(text = stringResource(id = R.string.text_heading4), modifier = Modifier.padding(top = hmrc_spacing_16))
        Text(
            text = stringResource(id = R.string.text_h4), style = typography.h4,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Heading5(text = stringResource(id = R.string.text_heading5), modifier = Modifier.padding(top = hmrc_spacing_16))
        Text(
            text = stringResource(id = R.string.text_h5), style = typography.h5,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = stringResource(id = R.string.text_bold), style = typography.h6,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = stringResource(id = R.string.text_body), style = typography.body,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = stringResource(id = R.string.text_info), style = typography.info,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
        Text(
            text = stringResource(id = R.string.text_error), style = typography.errorText,
            modifier = Modifier.padding(top = hmrc_spacing_16)
        )
    }
}
