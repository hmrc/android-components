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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.molecule.inset.InsetView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_4
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_8
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.extension.addPlaceholderModifier
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesHeading
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderHeading
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun InsetViewScreen() {
    ScreenScrollViewColumn {
        PlaceholderHeading()

        InsetView(modifier = Modifier
            .padding(bottom = hmrc_spacing_16)
            .addPlaceholderModifier(),
            childView = {
                Text(
                    text = stringResource(R.string.inset_placeholder_text),
                    style = typography.body,
                    textAlign = TextAlign.Start
                )
            }
        )

        ExamplesHeading()

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0),
            colors = CardDefaults.cardColors(containerColor = colors.hmrcWhiteBackground)) {
            InsetView(modifier = Modifier.padding(hmrc_spacing_16),
                childView = {
                    Column() {
                        Row {
                            Text(
                                text = stringResource(R.string.inset_example_text_line_1_description),
                                modifier = Modifier.padding(end = hmrc_spacing_4),
                                style = typography.body,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = stringResource(id = R.string.inset_example_text_line_1_value),
                                style = typography.h6,
                                textAlign = TextAlign.Start
                            )
                        }
                        Row(modifier = Modifier.padding(top = hmrc_spacing_8)) {
                            Text(
                                text = stringResource(R.string.inset_example_text_line_2_description),
                                modifier = Modifier.padding(end = hmrc_spacing_4),
                                style = typography.body,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = stringResource(id = R.string.inset_example_text_line_2_value),
                                style = typography.h6,
                                textAlign = TextAlign.Start
                            )
                        }
                        Row(modifier = Modifier.padding(top = hmrc_spacing_8)) {
                            Text(
                                text = stringResource(R.string.inset_example_text_line_3_description),
                                modifier = Modifier.padding(end = hmrc_spacing_4),
                                style = typography.body,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = stringResource(id = R.string.inset_example_text_line_3_value),
                                style = typography.h6,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            )
        }
    }
}