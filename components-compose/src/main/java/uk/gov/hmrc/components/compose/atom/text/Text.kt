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
package uk.gov.hmrc.components.compose.atom.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

@Composable
fun Text(text: String, style: TextStyle, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, style = style)
}

@Composable
fun H3Text(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.h3, modifier = modifier)
}

@Composable
fun H4Text(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.h4, modifier = modifier)
}

@Composable
fun H5Text(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.h5, modifier = modifier)
}

@Composable
fun BoldText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.h6, modifier = modifier)
}

@Composable
fun BodyText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.body, modifier = modifier)
}

@Composable
fun InfoText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.info, modifier = modifier)
}

@Composable
fun LinkText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.link, modifier = modifier)
}

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.errorText, modifier = modifier)
}

@Composable
fun TabBarText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = typography.tabBarText, modifier = modifier)
}
