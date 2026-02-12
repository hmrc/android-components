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
package uk.gov.hmrc.components.compose.organism.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.heading.Heading5Blue
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlackDark
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Suppress("LongMethod")
@Composable
fun MenuPanelRowView(
    heading: String,
    body: String? = null,
    hasNotification: Boolean = false,
    notification: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Int = R.drawable.components_ic_chevron_right,
    accessibilityButton: String? = "Button"
) {
    HmrcCardView(
        customBackgroundColor = HmrcTheme.colors.hmrcGrey3,
        modifier = modifier
            .clickable(
                onClick = onClick,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(HmrcTheme.dimensions.hmrcSpacing16),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Heading5Blue(
                        text = heading,
                        modifier = Modifier.semantics {
                            contentDescription =
                                if (body.isNullOrBlank()) heading + accessibilityButton else ""
                        }
                    )

                    Spacer(modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing4))

                    if (hasNotification) {
                        Box(
                            modifier = Modifier
                                .size(
                                    width = if (notification != null) 48.dp else 24.dp, // Just to show a red dot
                                    height = 24.dp,
                                )
                                .background(
                                    color = HmrcTheme.colors.hmrcRed,
                                    shape = RoundedCornerShape(size = HmrcTheme.dimensions.hmrcSpacing32)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (notification != null) {
                                Text(
                                    text = notification,
                                    style = HmrcTheme.typography.notificationText,
                                )
                            }
                        }
                    }
                }

                if (body != null) {
                    Spacer(modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing8))

                    BodyText(
                        text = body,
                        modifier = Modifier.semantics {
                            contentDescription = body + accessibilityButton
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing8))
            Image(
                painter = painterResource(id = icon),
                colorFilter = ColorFilter.tint(
                    if (isSystemInDarkTheme()) {
                        HmrcBlackDark
                    } else {
                        HmrcBlack
                    }
                ),
                contentDescription = "",
            )
        }
    }
}

@Preview
@Composable
fun MenuPanelRowViewPreview() {
    HmrcTheme() {
        MenuPanelRowView(
            heading = "Heading",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun MenuPanelRowViewExternalPreview() {
    HmrcTheme() {
        MenuPanelRowView(
            heading = "Heading",
            onClick = {},
            icon = R.drawable.ic_open_in_external_browser

        )
    }
}

@Preview
@Composable
fun MenuPanelRowViewBodyPreview() {
    HmrcTheme() {
        MenuPanelRowView(
            heading = "Heading",
            onClick = {},
            body = "Body"

        )
    }
}

@Preview
@Composable
fun MenuPanelRowViewNotificationPreview() {
    HmrcTheme() {
        MenuPanelRowView(
            heading = "Heading",
            onClick = {},
            hasNotification = true

        )
    }
}

@Preview
@Composable
fun MenuPanelRowViewNotificationsPreview() {
    HmrcTheme() {
        MenuPanelRowView(
            heading = "Heading",
            onClick = {},
            hasNotification = true,
            notification = "2"

        )
    }
}
