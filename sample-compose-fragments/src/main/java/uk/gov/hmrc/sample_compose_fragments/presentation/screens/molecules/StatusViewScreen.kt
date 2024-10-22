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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.statusview.StatusView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HMRCPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object StatusViewScreen {

    @Composable
    operator fun invoke() {
        ScreenScrollViewColumn {

            //region Place Holder
            PlaceholderSlot {
                StatusView(
                    title = stringResource(id = R.string.status_card_placeholder_title),
                    body = stringResource(id = R.string.status_card_placeholder_body),
                    icon = R.drawable.ic_maintenance,
                    iconTint = HmrcTheme.colors.hmrcGrey1,
                    primaryButtonText = stringResource(id = R.string.status_card_placeholder_primary_button),
                    secondaryButtonText = stringResource(id = R.string.status_card_placeholder_secondary_button),
                    infoText = stringResource(id = R.string.status_card_placeholder_info_text)
                )
            }
            //endregion


            ExamplesSlot {
                //region Example 1
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    StatusView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = stringResource(id = R.string.status_card_example_1_title),
                        body = stringResource(id = R.string.status_card_example_1_body),
                        icon = R.drawable.ic_maintenance,
                    )
                }
                //endregion

                //region Example 2
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    StatusView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = stringResource(id = R.string.status_card_example_2_title),
                        icon = R.drawable.ic_info,
                        iconTint = HmrcTheme.colors.hmrcGrey1
                    )
                }
                //endregion

                //region Example 3
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    StatusView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = stringResource(id = R.string.longer_text),
                        body = stringResource(id = R.string.longest_text),
                        icon = R.drawable.ic_coins,
                        bodyAlignment = StatusView.BodyAlignment.START,
                        iconTint = HmrcTheme.colors.hmrcGreen1,
                        textColor = HmrcTheme.colors.hmrcGreen1
                    )
                }
                //endregion

                //region Example 4
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    StatusView(
                        modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                        title = stringResource(id = R.string.status_card_example_4_title),
                        body = stringResource(id = R.string.status_card_example_4_body),
                        icon = R.drawable.ic_info,
                        infoText = stringResource(id = R.string.status_card_example_4_info),
                        secondaryButtonText = stringResource(id = R.string.status_card_example_4_button)
                    )
                }
                //endregion
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun StatusViewScreenPreview() {
    HMRCPreview {
        StatusViewScreen()
    }
}
