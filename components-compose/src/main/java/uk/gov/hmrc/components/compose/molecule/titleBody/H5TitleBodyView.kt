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
package uk.gov.hmrc.components.compose.molecule.titleBody

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_8

@Composable
fun H5TitleBodyView(
    H5Text: String,
    bodyText: String,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(modifier = modifier) {
            Heading5(
                text = H5Text,
                modifier = Modifier.padding(vertical = hmrc_spacing_8)
            )
        }
        Row(modifier = modifier) {
            Text(
                text = bodyText,
                modifier = Modifier.padding(vertical = hmrc_spacing_8)
            )
        }
    }
}
