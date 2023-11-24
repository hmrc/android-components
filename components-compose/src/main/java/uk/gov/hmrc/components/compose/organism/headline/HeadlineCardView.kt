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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.heading.Heading3
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object HeadlineCardView {
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        headline: String,
        title: String,
        isClickable: Boolean = false,
        childPadding: Boolean = true,
        views: List<@Composable () -> Unit> = listOf(),
        onHeadlineCardClick: () -> Unit = {}
    ) {
        if (isClickable) {
            CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
                Row(
                    modifier = Modifier
                        .background(HmrcTheme.colors.hmrcWhiteBackground)
                        .clickable {
                            onHeadlineCardClick()
                        }
                ) {
                    HeadlineCard(modifier = modifier.then(Modifier.weight(1f)), headline, title, childPadding, views)
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.components_ic_chevron_right),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing8))
                }
            }
        } else {
            HeadlineCard(modifier, headline, title, childPadding, views)
        }
    }

    @Composable
    fun HeadlineCard(
        modifier: Modifier = Modifier,
        headline: String,
        title: String,
        childPadding: Boolean = true,
        views: List<@Composable () -> Unit> = listOf()
    ) {
        Column(
            modifier = modifier
                .background(HmrcTheme.colors.hmrcWhiteBackground)
                .fillMaxWidth()
        ) {
            Heading5(
                text = title,
                modifier = Modifier
                    .padding(HmrcTheme.dimensions.hmrcSpacing16)
                    .fillMaxWidth()
            )
            Heading3(
                text = headline,
                modifier = Modifier
                    .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (childPadding) {
                            Modifier.padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                        } else {
                            Modifier.padding(0.dp)
                        }
                    )
            ) {
                views.forEach { view ->
                    if (childPadding) Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
                    view()
                }
            }
            if (childPadding) Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
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
            isClickable = true,
            views = listOf {
                Text(
                    text = "Body",
                    style = HmrcTheme.typography.body
                )
            }
        )
    }
}
