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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun ProvideHmrcTheme(
    dimensions: Dimensions,
    orientation: Orientation,
    colors: HmrcColors,
    typography: HmrcTypography,
    content: @Composable () -> Unit
) {
    // Explicitly creating a new object here so we don't mutate the initial [colors] provided,
    // and overwrite the values set in it.

    val dimSet = remember { dimensions }
    val orientation = remember { orientation }

    val colorPalette = remember { colors.copy() }
    val typographySet = remember { typography.copy() }
    colorPalette.update(colors)
    CompositionLocalProvider(
        LocalAppDimens provides dimSet,
        LocalOrientationMode provides orientation,
        LocalHmrcColors provides colorPalette,
        LocalHmrcTypography provides typographySet,
        content = content
    )
}

val LocalHmrcColors = staticCompositionLocalOf<HmrcColors> { error("No HmrcColorPalette provided") }
val LocalHmrcTypography = staticCompositionLocalOf<HmrcTypography> { error("No HmrcTypography provided") }

val LocalAppDimens = compositionLocalOf { smallDimensions }
val LocalOrientationMode = compositionLocalOf { Orientation.Portrait }
