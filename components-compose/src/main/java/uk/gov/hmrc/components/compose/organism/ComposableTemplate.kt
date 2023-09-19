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
package uk.gov.hmrc.components.compose.organism

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun HmrcCardView(
    modifier: Modifier? = Modifier,
    elevation: CardElevation = CardDefaults.cardElevation(),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier?.fillMaxWidth() ?: Modifier.fillMaxWidth(),
        shape = RectangleShape,
        content = content,
        colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground),
        elevation = elevation
    )
}
