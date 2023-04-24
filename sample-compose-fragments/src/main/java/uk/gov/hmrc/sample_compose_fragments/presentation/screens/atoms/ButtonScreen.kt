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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.button.IconButton
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.atom.button.SecondaryButton
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun ButtonScreen() {
    ScreenScrollViewColumn {
        PrimaryButton(text = stringResource(id = R.string.button_primary_example)) {}
        Spacer(modifier = Modifier.height(dimensions.hmrc_spacing_16))
        PrimaryButton(text = stringResource(id = R.string.button_primary_example_disabled), enabled = false) {}
        Spacer(modifier = Modifier.height(dimensions.hmrc_spacing_16))
        SecondaryButton(text = stringResource(id = R.string.button_secondary_example), onClick = {})
        Spacer(modifier = Modifier.height(dimensions.hmrc_spacing_16))
        IconButton(
            text = stringResource(id = R.string.button_icon_example),
            iconResId = R.drawable.ic_info,
            onClick = {}
        )
    }
}