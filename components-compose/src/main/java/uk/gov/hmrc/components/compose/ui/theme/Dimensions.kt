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
    val hmrcDividerHeight: Dp,
    val hmrcSpacing4: Dp,
    val hmrcSpacing8: Dp,
    val hmrcSpacing16: Dp,
    val hmrcSpacing24: Dp,
    val hmrcSpacing48: Dp,
    val hmrcIconSize24: Dp,
    val hmrcIconSize36: Dp,
    val hmrcIconSize100: Dp,
    val hmrcButtonSize48: Dp,
)

val smallDimensions = Dimensions(
    hmrcDividerHeight = 1.dp,
    hmrcSpacing4 = 4.dp,
    hmrcSpacing8 = 8.dp,
    hmrcSpacing16 = 16.dp,
    hmrcSpacing24 = 24.dp,
    hmrcSpacing48 = 48.dp,
    hmrcIconSize24 = 24.dp,
    hmrcIconSize36 = 36.dp,
    hmrcIconSize100 = 100.dp,
    hmrcButtonSize48 = 48.dp
)

val compactDimensions = Dimensions(
    hmrcDividerHeight = 1.dp,
    hmrcSpacing4 = 4.dp,
    hmrcSpacing8 = 8.dp,
    hmrcSpacing16 = 16.dp,
    hmrcSpacing24 = 24.dp,
    hmrcSpacing48 = 48.dp,
    hmrcIconSize24 = 24.dp,
    hmrcIconSize36 = 36.dp,
    hmrcIconSize100 = 100.dp,
    hmrcButtonSize48 = 48.dp
)

val mediumDimensions = Dimensions(
    hmrcDividerHeight = 1.dp,
    hmrcSpacing4 = 4.dp,
    hmrcSpacing8 = 8.dp,
    hmrcSpacing16 = 16.dp,
    hmrcSpacing24 = 24.dp,
    hmrcSpacing48 = 48.dp,
    hmrcIconSize24 = 24.dp,
    hmrcIconSize36 = 36.dp,
    hmrcIconSize100 = 100.dp,
    hmrcButtonSize48 = 48.dp
)

val largeDimensions = Dimensions(
    hmrcDividerHeight = 1.dp,
    hmrcSpacing4 = 4.dp,
    hmrcSpacing8 = 8.dp,
    hmrcSpacing16 = 16.dp,
    hmrcSpacing24 = 24.dp,
    hmrcSpacing48 = 48.dp,
    hmrcIconSize24 = 24.dp,
    hmrcIconSize36 = 36.dp,
    hmrcIconSize100 = 100.dp,
    hmrcButtonSize48 = 48.dp
)
