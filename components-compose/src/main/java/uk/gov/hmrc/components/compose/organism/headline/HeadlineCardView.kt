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
package uk.gov.hmrc.components.compose.organism.headline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object HeadlineCardView {

    private const val TRAVERSE_INDEX_1ST = 1F
    private const val TRAVERSE_INDEX_2ND = 2F
    private const val TRAVERSE_INDEX_3RD = 3F
    private const val TRAVERSE_INDEX_4TH = 4F

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        headline: String,
        headlineContentDescription: String = "",
        title: String,
        titleContentDescription: String = "",
        childPadding: Boolean = true,
        chevronContentDescription: String = "",
        onHeadlineCardClick: (() -> Unit)? = null,
        content: @Composable (ColumnScope.() -> Unit)? = null
    ) {
        if (onHeadlineCardClick != null) {
            CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
                Row(
                    modifier = Modifier
                        .background(HmrcTheme.colors.hmrcWhiteBackground)
                        .clickable { onHeadlineCardClick() }
                ) {
                    HeadlineCard(
                        modifier = modifier.then(
                            Modifier
                                .weight(1f)
                                .semantics(mergeDescendants = true) {}
                        ),
                        headline, headlineContentDescription, title, titleContentDescription, childPadding, content
                    )
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .semantics(mergeDescendants = true) {
                                contentDescription = chevronContentDescription
                                traversalIndex = TRAVERSE_INDEX_4TH
                                role = Role.Button
                            }
                            .clickable { onHeadlineCardClick() },
                        painter = painterResource(id = R.drawable.components_ic_chevron_right),
                        contentDescription = chevronContentDescription
                    )
                    Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing8))
                }
            }
        } else {
            HeadlineCard(
                modifier,
                headline,
                headlineContentDescription,
                title,
                titleContentDescription,
                childPadding,
                content
            )
        }
    }

    @Composable
    fun HeadlineCard(
        modifier: Modifier = Modifier,
        headline: String,
        headlineContentDescription: String = "",
        title: String,
        titleContentDescription: String = "",
        childPadding: Boolean = true,
        content: @Composable (ColumnScope.() -> Unit)? = null
    ) {

        val childPaddingState by remember { derivedStateOf { childPadding } }

        Column(
            modifier = modifier
                .background(HmrcTheme.colors.hmrcWhiteBackground)
                .fillMaxWidth()
        ) {
            Heading5(
                text = title,
                modifier = Modifier
                    .padding(HmrcTheme.dimensions.hmrcSpacing16)
                    .semantics(mergeDescendants = true) {
                        contentDescription = titleContentDescription
                        traversalIndex = TRAVERSE_INDEX_1ST
                    }
                    .fillMaxWidth()
            )
            Text(
                text = headline,
                style = HmrcTheme.typography.h3,
                modifier = Modifier
                    .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                    .semantics(mergeDescendants = true) {
                        contentDescription = headlineContentDescription
                        traversalIndex = TRAVERSE_INDEX_2ND
                    }
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            if (content != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics(mergeDescendants = true) {
                            isTraversalGroup = true
                            traversalIndex = TRAVERSE_INDEX_3RD
                        }
                        .then(
                            if (childPaddingState) {
                                Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16)
                            } else {
                                Modifier
                            }
                        )
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun HeadlineCardViewPreview() {
    HmrcTheme() {
        HeadlineCardView(
            modifier = Modifier,
            title = "Title",
            headline = "Headline",
            content = {
                Text(
                    text = "Body",
                    style = HmrcTheme.typography.body
                )
            }
        )
    }
}

@Preview
@Composable
fun HeadlineCardViewPreviewWithChevron() {
    HmrcTheme() {
        HeadlineCardView(
            title = "Title",
            headline = "Headline",
            onHeadlineCardClick = {},
            content = {
                Text(
                    text = "Body",
                    style = HmrcTheme.typography.body
                )
            }
        )
    }
}
