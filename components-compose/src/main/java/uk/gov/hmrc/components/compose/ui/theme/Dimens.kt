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
package uk.gov.hmrc.components.compose.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val hmrc_spacing_4: Dp,
    val hmrc_spacing_8: Dp,
    val hmrc_spacing_16: Dp,
    val hmrc_icon_size_24: Dp,
    val hmrc_button_size_48: Dp,
)

val smallDimensions = Dimensions(
    hmrc_spacing_4 = 4.dp,
    hmrc_spacing_8 = 8.dp,
    hmrc_spacing_16 = 16.dp,
    hmrc_icon_size_24 = 24.dp,
    hmrc_button_size_48 = 48.dp
)

val compactDimensions = Dimensions(
    hmrc_spacing_4 = 4.dp,
    hmrc_spacing_8 = 8.dp,
    hmrc_spacing_16 = 16.dp,
    hmrc_icon_size_24 = 24.dp,
    hmrc_button_size_48 = 48.dp
)

val mediumDimensions = Dimensions(
    hmrc_spacing_4 = 4.dp,
    hmrc_spacing_8 = 8.dp,
    hmrc_spacing_16 = 16.dp,
    hmrc_icon_size_24 = 24.dp,
    hmrc_button_size_48 = 48.dp
)

val largeDimensions = Dimensions(
    hmrc_spacing_4 = 4.dp,
    hmrc_spacing_8 = 8.dp,
    hmrc_spacing_16 = 16.dp,
    hmrc_icon_size_24 = 24.dp,
    hmrc_button_size_48 = 48.dp
)

val hmrc_spacing_4 = 4.dp
val hmrc_spacing_8 = 8.dp
val hmrc_spacing_16 = 16.dp
val hmrc_icon_size_24 = 24.dp
val hmrc_button_size_48 = 48.dp
