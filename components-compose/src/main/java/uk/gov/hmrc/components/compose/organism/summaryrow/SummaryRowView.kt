/*
 * Copyright 2024 HM Revenue & Customs
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
package uk.gov.hmrc.components.compose.organism.summaryrow

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

object SummaryRowView {

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        titleText: String,
        isBoldTitleTextAppearance: Boolean = true,
        titleMaxLines: Int = -1,
        icon: Painter = painterResource(id = R.drawable.components_ic_chevron_right),
        accessibilityMessage: String? = null,
        rows: List<@Composable () -> Unit>,
        onSummaryRowClicked: (() -> Unit)? = null
    ) {
        if (onSummaryRowClicked != null) {
            CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
                Row(
                    modifier = Modifier.clickable { onSummaryRowClicked.invoke() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SummaryRow(
                        modifier = modifier.then(
                            Modifier
                                .padding(
                                    top = dimensions.hmrcSpacing16,
                                    bottom = dimensions.hmrcSpacing16,
                                    start = dimensions.hmrcSpacing16
                                )
                                .weight(1f)
                        ),
                        titleText = titleText,
                        titleMaxLines = titleMaxLines,
                        isBoldTitleTextAppearance = isBoldTitleTextAppearance,
                        rows = rows
                    )
                    Spacer(modifier = Modifier.width(dimensions.hmrcSpacing8))
                    Image(painter = icon, contentDescription = "")
                    Spacer(modifier = Modifier.width(dimensions.hmrcSpacing16))
                }
            }
        } else {
            SummaryRow(
                modifier = modifier.padding(dimensions.hmrcSpacing16),
                titleText = titleText,
                titleMaxLines = titleMaxLines,
                isBoldTitleTextAppearance = isBoldTitleTextAppearance,
                rows = rows
            )
        }
    }

    @Composable
    private fun SummaryRow(
        modifier: Modifier = Modifier,
        titleText: String,
        isBoldTitleTextAppearance: Boolean,
        titleMaxLines: Int = -1,
        rows: List<@Composable () -> Unit>
    ) {
        Column(modifier = modifier) {
            Text(
                text = titleText,
                style = if (isBoldTitleTextAppearance) typography.h6 else typography.body,
                maxLines = if (titleMaxLines == -1) Int.MAX_VALUE else titleMaxLines
            )
            rows.forEach { rowItem ->
                Spacer(modifier = Modifier.height(dimensions.hmrcSpacing8))
                rowItem()
            }
        }
    }
}
