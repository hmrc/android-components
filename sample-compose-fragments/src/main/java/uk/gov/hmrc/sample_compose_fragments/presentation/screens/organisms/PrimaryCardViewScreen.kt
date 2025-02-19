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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import uk.gov.hmrc.components.compose.atom.button.IconButton
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.atom.button.SecondaryButton
import uk.gov.hmrc.components.compose.molecule.inset.InsetTextView
import uk.gov.hmrc.components.compose.organism.primary.PrimaryCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun PrimaryCardViewScreen(onClickAction: () -> Unit) {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            PrimaryCardView(stringResource(id = R.string.primary_card_placeholder_title)) {
                Text(
                    text = stringResource(id = R.string.primary_card_placeholder_body),
                    style = HmrcTheme.typography.body
                )
            }
        }

        ExamplesSlot {
            PrimaryCardView(
                title = stringResource(id = R.string.primary_card_example_1_title),
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
            ) {
                Text(
                    text = stringResource(id = R.string.primary_card_example_1_body),
                    style = HmrcTheme.typography.body
                )
            }
            PrimaryCardView(
                title = stringResource(id = R.string.longer_text),
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
            ) {
                Text(
                    text = stringResource(id = R.string.longest_text),
                    style = HmrcTheme.typography.body
                )
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
                InsetTextView(text = stringResource(id = R.string.longer_text))
            }
            PrimaryCardView(
                title = stringResource(id = R.string.primary_card_example_1_title),
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
            ) {
                Text(
                    text = stringResource(id = R.string.primary_card_example_1_body),
                    style = HmrcTheme.typography.body
                )
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
                PrimaryButton(text = stringResource(id = R.string.primary_card_example_3_button))
                { onClickAction() }
            }
            PrimaryCardView(
                title = stringResource(id = R.string.longer_text),
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                childPadding = false
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = HmrcTheme.dimensions.hmrcSpacing16,
                            end = HmrcTheme.dimensions.hmrcSpacing16,
                            top = HmrcTheme.dimensions.hmrcSpacing16
                        ),
                    text = stringResource(id = R.string.longest_text),
                    style = HmrcTheme.typography.body
                )
                SecondaryButton(text = stringResource(id = R.string.long_text),
                    textAlign = TextAlign.Start,
                    onClick = { onClickAction() })
            }
            PrimaryCardView(
                title = stringResource(id = R.string.longer_text),
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                childPadding = false
            ) {
                IconButton(text = stringResource(id = R.string.long_text),
                    iconResId = R.drawable.ic_info,
                    onClick = { onClickAction() })
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun PrimaryCardViewScreenPreview() {
    HmrcPreview {
        PrimaryCardViewScreen {}
    }
}
