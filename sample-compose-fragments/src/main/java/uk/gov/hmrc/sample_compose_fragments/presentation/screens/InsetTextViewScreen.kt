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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.molecule.inset.InsetTextView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.extension.addPlaceholderModifier

@Composable
fun InsetTextViewScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(hmrc_spacing_16)
            .verticalScroll(rememberScrollState())
    ) {
        Heading5(text = stringResource(id = R.string.heading_placeholder_plural),
            modifier = Modifier.padding(bottom = hmrc_spacing_16))

        InsetTextView(
            text = stringResource(id = R.string.inset_text_placeholder_text),
            modifier = Modifier
                .padding(bottom = hmrc_spacing_16)
                .addPlaceholderModifier())

        Heading5(text = stringResource(id = R.string.heading_example_plural),
            modifier = Modifier.padding(bottom = hmrc_spacing_16))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0),
            colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)) {
            InsetTextView(
                text = stringResource(id = R.string.inset_text_example_text),
                modifier = Modifier.padding(hmrc_spacing_16)
            )
        }
    }
}
