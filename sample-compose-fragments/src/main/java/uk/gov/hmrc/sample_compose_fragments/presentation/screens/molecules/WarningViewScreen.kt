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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.R as componentR
import uk.gov.hmrc.components.compose.molecule.warningview.WarningView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object WarningViewScreen {

    @Composable
    operator fun invoke() {
        ScreenScrollViewColumn {
            PlaceholderSlot {
                WarningView(
                    text = stringResource(id = R.string.status_card_placeholder_body)
                )
            }

            ExamplesSlot {
                //Example one
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    WarningView(
                        text = stringResource(id = R.string.warning_example_1_text)
                    )
                }

                //Example two
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    WarningView(
                        text = stringResource(id = R.string.warning_example_2_text)
                    )
                }

                //Example three
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    WarningView(
                        modifier = Modifier.background(HmrcTheme.colors.hmrcBlue),
                        text = stringResource(id = R.string.longest_text),
                        icon = componentR.drawable.components_ic_warning,
                        iconTintColor = HmrcTheme.colors.hmrcWhite,
                        textColor = HmrcTheme.colors.hmrcWhite
                    )
                }

                // Example four
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    WarningView(
                        modifier = Modifier.background(HmrcTheme.colors.hmrcWhite),
                        padding = 0.dp,
                        text = stringResource(id = R.string.warning_example_3_text),
                        iconTintColor = HmrcTheme.colors.hmrcRed,
                        textColor = HmrcTheme.colors.hmrcRed,
                    )
                }
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun WarningViewScreenPreview() {
    HmrcPreview {
        WarningViewScreen()
    }
}
