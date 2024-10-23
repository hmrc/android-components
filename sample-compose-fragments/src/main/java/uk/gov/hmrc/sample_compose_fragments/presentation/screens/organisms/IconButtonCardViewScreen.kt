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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.organism.card.IconButtonCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun IconButtonCardViewScreen(onClickAction: () -> Unit) {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            IconButtonCardView(
                titleText = stringResource(id = R.string.icon_button_placeholder_title),
                iconResId = R.drawable.ic_help_outline,
                onClick = { onClickAction() }
            )
        }

        ExamplesSlot {
            IconButtonCardView(
                titleText = stringResource(id = R.string.icon_button_example_1_title),
                iconResId = R.drawable.ic_help_outline,
                onClick = { onClickAction() },
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
            )

            IconButtonCardView(
                titleText = stringResource(id = R.string.longer_text),
                iconResId = R.drawable.ic_help_outline,
                onClick = { onClickAction() },
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
            )
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun IconButtonCardViewScreenPreview() {
    HmrcPreview {
        IconButtonCardViewScreen {}
    }
}
