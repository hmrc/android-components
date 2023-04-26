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

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

object MultiColumnRowView {

    private const val restrictedColumnCount = 3
    private const val WEIGHT_FULL = 1f
    private const val WEIGHT_HALF = 0.5f

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        columnList: List<MultiColumnRowItem>
    ) {
        Row(modifier = modifier) {
            val restrictedColumns = columnList.take(restrictedColumnCount)
            restrictedColumns.forEachIndexed { index, column ->

                val textAlign = if (index == 0) TextAlign.Start else TextAlign.End

                val columnWeight =
                    if (restrictedColumns.size == 1 || (restrictedColumns.size == 2 && index == 0)) WEIGHT_FULL
                    else WEIGHT_HALF

                Text(
                    text = column.text,
                    style = column.textStyle.invoke().copy(textAlign = textAlign),
                    modifier = column.modifier.weight(columnWeight)
                )
            }
        }
    }
}
