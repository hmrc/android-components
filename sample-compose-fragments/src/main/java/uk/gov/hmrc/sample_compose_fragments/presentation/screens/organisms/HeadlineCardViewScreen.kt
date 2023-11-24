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
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.headline.HeadlineCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object HeadlineCardViewScreen {
    @Composable
    operator fun invoke(
        onClickAction: () -> Unit
    ) {
        ScreenScrollViewColumn {
            PlaceholderSlot {
                HeadlineCardView(
                    title = stringResource(id = R.string.headline_placeholder_title),
                    headline = stringResource(id = R.string.headline_placeholder_headline),
                    isClickable = true,
                    views = listOf {
                        Text(
                            text = stringResource(id = R.string.headline_placeholder_body),
                            style = HmrcTheme.typography.body
                        )
                    }
                ) {
                    onClickAction()
                }
            }

            ExamplesSlot {
                // Example 1
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.headline_example_title),
                        headline = stringResource(id = R.string.headline_example_headline),
                        isClickable = true,
                        views = listOf {
                            Text(
                                text = stringResource(id = R.string.headline_example_body),
                                style = HmrcTheme.typography.body
                            )
                        }
                    ) {
                        onClickAction()
                    }
                }

                // Example 2
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.longer_text),
                        headline = stringResource(id = R.string.long_text),
                        views = listOf {
                            Text(
                                text = stringResource(id = R.string.longest_text),
                                style = HmrcTheme.typography.body
                            )
                        }
                    )
                }

                // Example 3
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.headline_example_title),
                        headline = stringResource(id = R.string.headline_example_headline),
                        views = listOf(
                            {
                                Text(
                                    text = stringResource(id = R.string.headline_example_body),
                                    style = HmrcTheme.typography.body
                                )
                            },
                            {
                                PrimaryButton(
                                    text = stringResource(id = R.string.headline_example_button),
                                    onClick = { onClickAction() },
                                )
                            }
                        )
                    )
                }

                // Example 4
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.longer_text),
                        headline = stringResource(id = R.string.long_text),
                        childPadding = false,
                        views = listOf(
                            { Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16)) },
                            {
                                Text(
                                    modifier = Modifier.padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16),
                                    text = stringResource(id = R.string.longest_text),
                                    style = HmrcTheme.typography.body
                                )
                            },
                            {
                                SecondaryButton(
                                    text = stringResource(id = R.string.long_text),
                                    textAlign = TextAlign.Start,
                                    onClick = { onClickAction() },
                                )
                            }
                        )
                    )
                }

                // Example 5
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.headline_example_title),
                        headline = stringResource(id = R.string.headline_example_headline),
                        childPadding = false,
                        views = listOf(
                            { Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16)) },
                            {
                                Text(
                                    modifier = Modifier.padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16),
                                    text = stringResource(id = R.string.headline_example_body),
                                    style = HmrcTheme.typography.body
                                )
                            },
                            {
                                IconButton(
                                    text = stringResource(id = R.string.headline_example_icon_button),
                                    iconResId = R.drawable.ic_info,
                                    onClick = { onClickAction() }
                                )
                            }
                        )
                    )
                }

                // Example 6
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.headline_example_title),
                        headline = stringResource(id = R.string.headline_example_headline),
                        views = listOf(
                            {
                                Text(
                                    text = stringResource(id = R.string.headline_example_body),
                                    style = HmrcTheme.typography.body
                                )
                            },
                            { InsetTextView(text = stringResource(id = R.string.inset_text_example_text)) }
                        )
                    )
                }

                // Example 7
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        title = stringResource(id = R.string.headline_example_7_title),
                        headline = stringResource(id = R.string.headline_example_7_headline)
                    )
                }
            }
        }
    }
}
