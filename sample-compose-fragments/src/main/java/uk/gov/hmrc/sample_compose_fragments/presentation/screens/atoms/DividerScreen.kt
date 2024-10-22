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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.divider.HmrcDivider
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HMRCPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object DividerScreen {

    @Composable
    operator fun invoke() {
        ScreenScrollViewColumn {
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            HmrcDivider()
            Text(
                text = stringResource(id = R.string.divider_example_text),
                style = HmrcTheme.typography.body,
                modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16)
            )
            HmrcDivider()
            Text(
                text = stringResource(id = R.string.divider_example_text),
                style = HmrcTheme.typography.body,
                modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16)
            )
            HmrcDivider()
            Text(
                text = stringResource(id = R.string.divider_example_text),
                style = HmrcTheme.typography.body,
                modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16)
            )
            HmrcDivider()
            Text(
                text = stringResource(id = R.string.divider_example_text),
                style = HmrcTheme.typography.body,
                modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16)
            )
            HmrcDivider()
        }
    }

}

@HmrcAllDevicePreview()
@Composable
fun DividerScreenPreview() {
    HMRCPreview {
        DividerScreen()
    }
}
