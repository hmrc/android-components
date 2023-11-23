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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.atom.heading.Heading3
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object HeadlineCardView {
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        headline: String,
        title: String,
        views: List<ComposableItem>
    ) {
        Column(
            modifier = modifier
        ) {
            Heading5(text = title)
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
            Heading3(text = headline)
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
            views.forEach { view ->
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
                Box(modifier = Modifier.clickable(onClick = view.onClick)) {
                    view.content()
                }
            }
        }
    }

    data class ComposableItem(
        val content: @Composable () -> Unit,
        val onClick: () -> Unit
    )
}

@Preview
@Composable
fun HeadlineCardViewPreview() {
    HmrcTheme() {
        HeadlineCardView(
            modifier = Modifier.background(HmrcTheme.colors.hmrcWhite),
            title = "Title",
            headline = "Headline",
            views = listOf(
                HeadlineCardView.ComposableItem(
                    content = { Text(text = "Hello") },
                    onClick = { println("Clicked Hello") }
                ),
                HeadlineCardView.ComposableItem(
                    content = {
                        PrimaryButton(
                            text = "Hello",
                            onClick = {},
                        )
                    },
                    onClick = { println("Clicked Hello") }
                )
            )
        )
    }
}
