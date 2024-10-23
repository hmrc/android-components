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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.bullet.BulletedTextView
import uk.gov.hmrc.components.compose.atom.heading.Heading3
import uk.gov.hmrc.components.compose.atom.heading.Heading4
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.atom.text.BoldText
import uk.gov.hmrc.components.compose.atom.text.ErrorText
import uk.gov.hmrc.components.compose.atom.text.H3Text
import uk.gov.hmrc.components.compose.atom.text.H4Text
import uk.gov.hmrc.components.compose.atom.text.H5Text
import uk.gov.hmrc.components.compose.atom.text.InfoText
import uk.gov.hmrc.components.compose.atom.text.LinkText
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun TextScreen() {
    ScreenScrollViewColumn {
        Heading3(text = stringResource(id = R.string.text_heading3))
        H3Text(
            text = stringResource(id = R.string.text_h3),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        Heading4(
            text = stringResource(id = R.string.text_heading4),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        H4Text(
            text = stringResource(id = R.string.text_h4),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        Heading5(
            text = stringResource(id = R.string.text_heading5),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        H5Text(
            text = stringResource(id = R.string.text_h5),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        BoldText(
            text = stringResource(id = R.string.text_bold),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        BodyText(
            text = stringResource(id = R.string.text_body),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        InfoText(
            text = stringResource(id = R.string.text_info),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        LinkText(
            text = stringResource(id = R.string.text_link),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        ErrorText(
            text = stringResource(id = R.string.text_error),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        BulletedTextView(
            text = stringResource(id = R.string.text_bullet_1),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
        BulletedTextView(
            text = stringResource(id = R.string.text_bullet_2),
            modifier = Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
        )
    }
}

@HmrcAllDevicePreview
@Composable
fun TextScreenPreview() {
    HmrcPreview {
        TextScreen()
    }
}
