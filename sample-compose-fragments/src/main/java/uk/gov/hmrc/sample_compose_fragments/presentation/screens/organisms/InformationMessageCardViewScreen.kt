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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.organism.information.InformationMessageButton
import uk.gov.hmrc.components.compose.organism.information.InformationMessageButton.Companion.ButtonType
import uk.gov.hmrc.components.compose.organism.information.InformationMessageCardView
import uk.gov.hmrc.components.compose.organism.information.InformationMessageType
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun InformationMessageCardViewScreen() {
    ScreenScrollViewColumn {
        PlaceholderSlot {
            InformationMessageCardView(
                headline = stringResource(id = R.string.info_message_placeholder_headline),
                text = stringResource(id = R.string.info_message_placeholder_headline),
                messageType = InformationMessageType.URGENT,
                buttons = listOf(InformationMessageButton("Headline button", onClick = {}))
            )
        }

        ExamplesSlot {
            // Example 1
            InformationMessageCardView(
                headline = stringResource(id = R.string.info_message_example_1_headline),
                messageType = InformationMessageType.INFO
            )

            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 2
            InformationMessageCardView(
                headline = stringResource(id = R.string.info_message_example_2_headline),
                messageType = InformationMessageType.WARNING,
                buttons = listOf(
                    InformationMessageButton("Do something", onClick = {}),
                    InformationMessageButton("Do something", ButtonType.OUTLINE, onClick = {})
                )
            )

            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 3
            InformationMessageCardView(
                headline = stringResource(id = R.string.info_message_example_3_headline),
                text = stringResource(id = R.string.info_message_example_3_body),
                headlineContentDescription = stringResource(id = R.string.info_message_example_3_headline_content_description),
                messageType = InformationMessageType.INFO,
                buttons = listOf(
                    InformationMessageButton("Do something", onClick = {}),
                    InformationMessageButton("Do something", ButtonType.OUTLINE, onClick = {})
                )
            )

            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 4
            InformationMessageCardView(
                headline = stringResource(id = R.string.info_message_example_4_headline),
                headlineContentDescription = stringResource(id = R.string.info_message_example_4_headline_content_description),
                messageType = InformationMessageType.NOTICE
            )
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun InformationMessageCardViewScreenPreview() {
    HmrcPreview {
        InformationMessageCardViewScreen()
    }
}
