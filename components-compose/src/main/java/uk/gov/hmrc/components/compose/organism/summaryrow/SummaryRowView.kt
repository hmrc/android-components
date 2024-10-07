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

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

object SummaryRowView {

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        titleText: String,
        isBoldTitleTextAppearance: Boolean = true,
        titleMaxLines: Int = -1,
        icon: Painter = painterResource(id = R.drawable.components_ic_chevron_right),
        accessibilityMessage: String? = null
    ) {
        Column(modifier = modifier) {
            Title(
                titleText = titleText,
                isBoldTitleTextAppearance = isBoldTitleTextAppearance,
                titleMaxLines = titleMaxLines
            )
        }
    }

    @Composable
    private fun Title(
        titleText: String,
        isBoldTitleTextAppearance: Boolean = true,
        titleMaxLines: Int = -1,
    ) {
        Text(
            text = titleText,
            style = if (isBoldTitleTextAppearance) typography.h6 else typography.body,
            maxLines = if (titleMaxLines == -1) Int.MAX_VALUE else titleMaxLines
        )
    }
}
