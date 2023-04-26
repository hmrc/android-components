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
package uk.gov.hmrc.components.compose.molecule.multiColumnRowView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MultiColumnRowView(
    modifier: Modifier = Modifier,
    columnList: List<MultiColumnRowItem>
) {
    Box(modifier = modifier) {
        Row {
            val columns = columnList.take(3) // Restricting 3 items
            columns.forEachIndexed { index, column ->
                val textAlign =
                    if (columns.size >= 2 && index >= 1) TextAlign.End else TextAlign.Start
                val weight =
                    if (columns.size == 1) 1f else if (columns.size == 2 && index == 0) 1f else 0.5f
                Text(
                    text = column.text,
                    style = column.textStyle.invoke().copy(textAlign = textAlign),
                    modifier = column.modifier.weight(weight)
                )
            }
        }
    }
}
