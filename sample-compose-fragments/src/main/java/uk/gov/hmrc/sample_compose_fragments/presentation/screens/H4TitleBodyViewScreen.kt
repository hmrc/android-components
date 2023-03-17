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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.titleBodyView.H4TitleBodyView
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.sample_compose_components.R

@Composable
fun H4TitleBodyViewScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = hmrc_spacing_16, end = hmrc_spacing_16)
            .verticalScroll(rememberScrollState())
    ) {
        H4TitleBodyView(H4Text = stringResource(id = R.string.h4_placeholder_title), H5Text = stringResource(id = R.string.h4_placeholder_body), rowModifier = Modifier.padding(top = hmrc_spacing_16))

        H4TitleBodyView(H4Text = stringResource(id = R.string.h4_example_title), H5Text = stringResource(id = R.string.h4_example_body), rowModifier = Modifier.padding(top = hmrc_spacing_16))

        H4TitleBodyView(H4Text = stringResource(id = R.string.long_text), H5Text = stringResource(id = R.string.longer_text), rowModifier = Modifier.padding(top = hmrc_spacing_16))

    }
}
