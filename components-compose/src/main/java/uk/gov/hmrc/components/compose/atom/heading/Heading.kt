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
package uk.gov.hmrc.components.compose.atom.heading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import uk.gov.hmrc.components.compose.ui.theme.CustomHmrcTypography
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors

@Composable
fun Heading(text: String, style: TextStyle, modifier: Modifier = Modifier, color: Color? = null) {
    val safeColor = color ?: colors.hmrcBlack
    Text(text = text, modifier = modifier.semantics { heading() }, style = style, color = safeColor)
}

@Composable
fun Heading3(text: String, modifier: Modifier = Modifier, color: Color? = null) {
    Heading(text = text, style = CustomHmrcTypography.h3, modifier = modifier, color)
}

@Composable
fun Heading4(text: String, modifier: Modifier = Modifier, color: Color? = null) {
    Heading(text = text, CustomHmrcTypography.h4, modifier = modifier, color)
}

@Composable
fun Heading5(text: String, modifier: Modifier = Modifier, color: Color? = null) {
    Heading(text = text, CustomHmrcTypography.h5, modifier = modifier, color)
}
