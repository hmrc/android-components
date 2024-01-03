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

import android.widget.Toast
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
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.primary.PrimaryCard
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun PrimaryCardViewScreen(onClickAction: () -> Unit) {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            PrimaryCard(
                title = stringResource(id = R.string.primary_card_placeholder_title),
                content = {
                    Text(
                        text = stringResource(id = R.string.primary_card_placeholder_body),
                        style = HmrcTheme.typography.body
                    )
                }
            )
        }

        ExamplesSlot {
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                PrimaryCard(
                    title = stringResource(id = R.string.primary_card_example_1_title),
                    content = {
                        Text(
                            text = stringResource(id = R.string.primary_card_example_1_body),
                            style = HmrcTheme.typography.body
                        )
                    }
                )
            }
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                PrimaryCard(
                    title = stringResource(id = R.string.primary_card_example_2_title),
                    content = {
                        Text(
                            text = stringResource(id = R.string.primary_card_example_2_body),
                            style = HmrcTheme.typography.body
                        )
                        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
                        InsetTextView(text = stringResource(id = R.string.primary_card_example_2_title))
                    }
                )
            }
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                PrimaryCard(
                    title = stringResource(id = R.string.primary_card_example_1_title),
                    content = {
                        Text(
                            text = stringResource(id = R.string.primary_card_example_1_body),
                            style = HmrcTheme.typography.body
                        )
                        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
                        PrimaryButton(text = stringResource(id = R.string.primary_card_example_3_button))
                        {onClickAction()}
                    }
                )
            }
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                PrimaryCard(
                    childPadding = false,
                    title = stringResource(id = R.string.primary_card_example_2_title),
                    content = {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                                .padding(top = HmrcTheme.dimensions.hmrcSpacing16),
                            text = stringResource(id = R.string.primary_card_example_2_body),
                            style = HmrcTheme.typography.body
                        )
                        SecondaryButton(text = stringResource(id = R.string.primary_d_carexample_4_button),
                            textAlign = TextAlign.Start,
                            onClick = {onClickAction()})
                    }
                )
            }
            HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                PrimaryCard(
                    childPadding = false,
                    title = stringResource(id = R.string.primary_card_example_2_title),
                    content = {
                        IconButton(text = stringResource(id = R.string.primary_d_carexample_4_button),
                            iconResId = R.drawable.ic_info,
                            onClick = {onClickAction()})
                    }
                )
            }
        }
    }
}