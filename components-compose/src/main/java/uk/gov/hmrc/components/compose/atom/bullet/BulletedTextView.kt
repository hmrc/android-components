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
package uk.gov.hmrc.components.compose.atom.bullet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.ui.extensions.enableTalkBackMergeAccessibility
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions

@Composable
fun BulletedTextView(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.enableTalkBackMergeAccessibility()) {
        BodyText(
            modifier = Modifier
                .padding(end = dimensions.hmrcSpacing16)
                .clearAndSetSemantics {},
            text = "\u25CF",
        )
        BodyText(
            text = text,
        )
    }
}
