/*
 * Copyright 2021 HM Revenue & Customs
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
package uk.gov.hmrc.components.compose.organism.primary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun PrimaryCard(
    modifier: Modifier = Modifier,
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
                .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                .padding(top = HmrcTheme.dimensions.hmrcSpacing16)
                .semantics(mergeDescendants = true) {
                    contentDescription = titleContentDescription
                }
                .fillMaxWidth()
        )
        if (content != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (childPaddingState) {
                            Modifier.padding(top = HmrcTheme.dimensions.hmrcSpacing16)
                                .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                        } else {
                            Modifier
                        }
                    )
            ) {
                content()
            }
            if (childPaddingState) Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        }
    }
}
