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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.atom.heading.Heading3
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object HeadlineCardView {
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        headline: String,
        title: String,
        childPadding: Boolean = true,
        views: List<@Composable () -> Unit> = listOf()
    ) {
        Column(
            modifier = modifier.background(HmrcTheme.colors.hmrcWhite).fillMaxWidth()
        ) {
            Heading5(text = title, modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16))
            Heading3(text = headline, modifier = Modifier.padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16))

            Column(
                modifier = Modifier.then(
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
            modifier = Modifier
                .background(HmrcTheme.colors.hmrcWhite),
            title = "Title",
            headline = "Headline",
            views = listOf {
                Text(
                    text = "Body",
                    style = HmrcTheme.typography.body
                )
            }
        )
    }
}
