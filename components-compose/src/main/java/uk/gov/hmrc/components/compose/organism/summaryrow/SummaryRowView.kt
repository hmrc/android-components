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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.focused
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

@Composable
fun SummaryRowView(
    titleText: String,
    rows: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    isBoldTitleTextAppearance: Boolean = true,
    islinkTitleTextAppearance: Boolean = false,
    titleMaxLines: Int = -1,
    icon: Painter = painterResource(id = R.drawable.components_ic_chevron_right),
    chevronContentDescription: String = "",
    readerTrait: ReaderTrait = ReaderTrait.READER_TRAIT_INFO,
    onSummaryRowClicked: (() -> Unit)? = null
) {
    if (onSummaryRowClicked != null) {
        CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
            Row(
                modifier = modifier.clickable { onSummaryRowClicked.invoke() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                SummaryRow(
                    modifier = Modifier
                        .padding(
                            top = dimensions.hmrcSpacing16,
                            bottom = dimensions.hmrcSpacing16,
                            start = dimensions.hmrcSpacing16
                        )
                        .weight(1f),
                    titleText = titleText,
                    titleMaxLines = titleMaxLines,
                    isBoldTitleTextAppearance = isBoldTitleTextAppearance,
                    islinkTitleTextAppearance = islinkTitleTextAppearance,
                    readerTrait = readerTrait,
                    rows = rows
                )
                Spacer(modifier = Modifier.width(dimensions.hmrcSpacing8))
                Image(
                    painter = icon,
                    modifier = Modifier
                        .semantics { role = Role.Button }
                        .clickable { onSummaryRowClicked() },
                    contentDescription = chevronContentDescription
                )
                Spacer(modifier = Modifier.width(dimensions.hmrcSpacing16))
            }
        }
    } else {
        SummaryRow(
            modifier = modifier.padding(dimensions.hmrcSpacing16),
            titleText = titleText,
            titleMaxLines = titleMaxLines,
            isBoldTitleTextAppearance = isBoldTitleTextAppearance,
            islinkTitleTextAppearance = islinkTitleTextAppearance,
            readerTrait = readerTrait,
            rows = rows
        )
    }
}

@Composable
private fun SummaryRow(
    titleText: String,
    isBoldTitleTextAppearance: Boolean,
    islinkTitleTextAppearance: Boolean,
    titleMaxLines: Int,
    readerTrait: ReaderTrait,
    rows: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { if (readerTrait == ReaderTrait.READER_TRAIT_INFO) focused = true },
            text = titleText,
            style = if (isBoldTitleTextAppearance) {
                typography.h6
            } else if (islinkTitleTextAppearance) {
                typography.link
            } else {
                typography.body
            },
            maxLines = if (titleMaxLines == -1) Int.MAX_VALUE else titleMaxLines,
            overflow = TextOverflow.Ellipsis
        )
        rows.forEach { rowItem ->
            Spacer(modifier = Modifier.height(dimensions.hmrcSpacing8))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { if (readerTrait == ReaderTrait.READER_TRAIT_INFO) focused = true }
            ) {
                rowItem()
            }
        }
    }
}

enum class ReaderTrait {
    READER_TRAIT_INFO,
    READER_TRAIT_SIMPLE
}
