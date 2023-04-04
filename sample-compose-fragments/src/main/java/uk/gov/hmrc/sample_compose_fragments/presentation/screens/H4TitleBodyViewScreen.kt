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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.titleBody.H4TitleBodyView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.extension.addPlaceholderModifier
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesHeading
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderHeading
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun H4TitleBodyViewScreen() {
    ScreenScrollViewColumn {

        PlaceholderHeading()

        H4TitleBodyView(
            titleText = stringResource(id = R.string.h4_placeholder_title),
            bodyText = stringResource(id = R.string.h4_placeholder_body),
            modifier = Modifier.addPlaceholderModifier()
        )

        Spacer(modifier = Modifier.padding(bottom = hmrc_spacing_16))

        ExamplesHeading()

        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = hmrc_spacing_16),
            shape = RoundedCornerShape(0),
            colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
        ) {
            H4TitleBodyView(
                titleText = stringResource(id = R.string.h4_example_title),
                bodyText = stringResource(id = R.string.h4_example_body),
                modifier = Modifier.padding(hmrc_spacing_16)
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(top = hmrc_spacing_16),
            shape = RoundedCornerShape(0),
            colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
        ) {
            H4TitleBodyView(
                titleText = stringResource(id = R.string.long_text),
                bodyText = stringResource(id = R.string.longer_text),
                modifier = Modifier.padding(hmrc_spacing_16)
            )
        }
    }
}
