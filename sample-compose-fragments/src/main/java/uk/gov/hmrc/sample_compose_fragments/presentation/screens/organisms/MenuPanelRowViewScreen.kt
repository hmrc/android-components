/*
 * Copyright 2026 HM Revenue & Customs
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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.organism.menu.MenuPanelRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.components.compose.R as composeR
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun MenuPanelRowViewScreen(onClickAction: () -> Unit) {
    ScreenScrollViewColumn {
        Column(
            modifier = Modifier
                .background(color = HmrcTheme.colors.hmrcWhiteBackground)
                .fillMaxWidth()
        ) {
        PlaceholderSlot{
            MenuPanelRowView(
                heading = stringResource(id = R.string.primary_card_placeholder_title),
                body = stringResource(id = R.string.primary_card_placeholder_body),
                onClick = onClickAction,
            )
        }

        ExamplesSlot {
            Column(
                modifier = Modifier.padding(horizontal = dimensions.hmrcSpacing8),
                verticalArrangement = Arrangement.spacedBy(dimensions.hmrcSpacing8)
            ) {
                MenuPanelRowView(
                    heading = stringResource(id = R.string.menu_panel_example_1_title),
                    body = stringResource(id = R.string.primary_card_example_1_body),
                    onClick = onClickAction,
                )

                MenuPanelRowView(
                    heading = stringResource(id = R.string.menu_panel_example_3_title),
                    body = stringResource(id = R.string.menu_panel_example_3_body),
                    hasNotification = true,
                    onClick = onClickAction,
                )

                MenuPanelRowView(
                    heading = stringResource(id = R.string.menu_panel_example_2_title),
                    body = stringResource(id = R.string.menu_panel_example_2_body),
                    hasNotification = true,
                    notification = "2",
                    onClick = onClickAction,
                )

                MenuPanelRowView(
                    heading = stringResource(id = R.string.menu_panel_example_4_title),
                    body = stringResource(id = R.string.menu_panel_example_4_body),
                    hasNotification = true,
                    notification = "99+",
                    onClick = onClickAction,
                )

                MenuPanelRowView(
                    heading = stringResource(id = R.string.menu_panel_example_5_title),
                    body = stringResource(id = R.string.menu_panel_example_5_body),
                    onClick = onClickAction,
                )

                MenuPanelRowView(
                    heading = stringResource(id = R.string.long_text),
                    body = stringResource(id = R.string.longer_text),
                    hasNotification = true,
                    notification = "NEW",
                    onClick = onClickAction,
                )
                MenuPanelRowView(
                    heading = stringResource(id = R.string.menu_panel_example_6_title),
                    body = stringResource(id = R.string.menu_panel_example_6_body),
                    onClick = onClickAction,
                    icon = composeR.drawable.ic_open_in_external_browser,
                    accessibilityDescription = composeR.string.accessibility_button_open_in_browser,
                )
            }
        }
    }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun MenuPanelRowViewScreenPreview() {
    HmrcPreview {
        MenuPanelRowViewScreen {}
    }
}