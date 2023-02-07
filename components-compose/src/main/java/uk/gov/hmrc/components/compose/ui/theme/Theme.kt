/*
 * Copyright 2021 HM Revenue & Customs
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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorPalette = HmrcColors(
    hmrcAlwaysBlack = HmrcAlwaysBlack,
    hmrcAlwaysWhite = HmrcAlwaysWhite,
    hmrcBlack = HmrcBlack,
    hmrcWhite = HmrcWhite,
    hmrcWhiteBackground = HmrcWhiteBackground,
    hmrcGreen1 = HmrcGreen1,
    hmrcGreen2 = HmrcGreen2,
    hmrcBlue = HmrcBlue,
    hmrcTeal = HmrcTeal,
    hmrcRed = HmrcRed,
    hmrcGrey1 = HmrcGrey1,
    hmrcGrey2 = HmrcGrey2,
    hmrcGrey3 = HmrcGrey3,
    hmrcPink = HmrcPink,
    hmrcYellow = HmrcYellow,
    isDark = false
)

private val DarkColorPalette = HmrcColors(
    hmrcAlwaysBlack = HmrcAlwaysBlack,
    hmrcAlwaysWhite = HmrcAlwaysWhite,
    hmrcBlack = HmrcBlackDark,
    hmrcWhite = HmrcWhiteDark,
    hmrcWhiteBackground = HmrcWhiteBackgroundDark,
    hmrcGreen1 = HmrcGreen1Dark,
    hmrcGreen2 = HmrcGreen2Dark,
    hmrcBlue = HmrcBlueDark,
    hmrcTeal = HmrcTealDark,
    hmrcRed = HmrcRedDark,
    hmrcGrey1 = HmrcGrey1Dark,
    hmrcGrey2 = HmrcGrey2Dark,
    hmrcGrey3 = HmrcGrey3Dark,
    hmrcPink = HmrcPinkDark,
    hmrcYellow = HmrcYellowDark,
    isDark = true
)

@Composable
fun HmrcTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideHmrcColors(colors) {
        MaterialTheme(
            typography = HmrcTypography,
            shapes = Shapes,
            content = content
        )
    }
}

enum class ColorItem(val colorName: String) {
    HmrcBlack("Black"), HmrcWhite("White"), HmrcGreen1("Green 1"),
    HmrcGreen2("Green 2"), HmrcBlue("Blue"), HmrcTeal("Teal"),
    HmrcRed("Red"), HmrcGrey1("Grey 1"), HmrcGrey2("Grey 2"),
    HmrcGrey3("Grey 3"), HmrcPink("Pink"), HmrcYellow("Yellow"),
    HmrcWhiteBackground("White background"), HmrcAlwaysBlack("Always black"),
    HmrcAlwaysWhite("Always white");

    val color: Color
        @Composable
        @ReadOnlyComposable
        get() {
            val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
            return when (this) {
                HmrcBlack -> colors.hmrcBlack
                HmrcWhite -> colors.hmrcWhite
                HmrcGreen1 -> colors.hmrcGreen1
                HmrcGreen2 -> colors.hmrcGreen2
                HmrcBlue -> colors.hmrcBlue
                HmrcTeal -> colors.hmrcTeal
                HmrcRed -> colors.hmrcRed
                HmrcGrey1 -> colors.hmrcGrey1
                HmrcGrey2 -> colors.hmrcGrey2
                HmrcGrey3 -> colors.hmrcGrey3
                HmrcPink -> colors.hmrcPink
                HmrcYellow -> colors.hmrcYellow
                HmrcWhiteBackground -> colors.hmrcWhiteBackground
                HmrcAlwaysBlack -> colors.hmrcAlwaysBlack
                HmrcAlwaysWhite -> colors.hmrcAlwaysWhite
            }
        }
}
object HmrcTheme {
    val colors: HmrcColors
        @Composable
        get() = LocalHmrcColors.current
}

/**
 * HMRC custom Color Palette
 */
@Stable
@SuppressWarnings("LongParameterList")
class HmrcColors(
    hmrcAlwaysBlack: Color,
    hmrcAlwaysWhite: Color,
    hmrcBlack: Color,
    hmrcWhite: Color,
    hmrcWhiteBackground: Color,
    hmrcGreen1: Color,
    hmrcGreen2: Color,
    hmrcBlue: Color,
    hmrcTeal: Color,
    hmrcRed: Color,
    hmrcGrey1: Color,
    hmrcGrey2: Color,
    hmrcGrey3: Color,
    hmrcPink: Color,
    hmrcYellow: Color,
    hmrcErrorText: Color = hmrcRed,
    hmrcDarkText: Color = hmrcBlack,
    hmrcLightText: Color = hmrcWhite,
    hmrcLinkText: Color = hmrcBlue,
    hmrcInfoText: Color = hmrcGrey1,
    hmrcPageBackground: Color = hmrcGrey3,
    hmrcDivider: Color = hmrcGrey2,
    hmrcInsetBar: Color = hmrcGrey2,
    hmrcInfoMessageWarningHeadlineBackground: Color = hmrcYellow,
    hmrcInfoMessageInfoHeadlineBackground: Color = hmrcBlue,
    hmrcInfoMessageUrgentHeadlineBackground: Color = hmrcRed,
    hmrcInfoMessageNoticeHeadlineBackground: Color = hmrcBlack,
    isDark: Boolean
) {
    var hmrcAlwaysBlack by mutableStateOf(hmrcAlwaysBlack)
        private set
    var hmrcAlwaysWhite by mutableStateOf(hmrcAlwaysWhite)
        private set
    var hmrcBlack by mutableStateOf(hmrcBlack)
        private set
    var hmrcWhite by mutableStateOf(hmrcWhite)
        private set
    var hmrcWhiteBackground by mutableStateOf(hmrcWhiteBackground)
        private set
    var hmrcGreen1 by mutableStateOf(hmrcGreen1)
        private set
    var hmrcGreen2 by mutableStateOf(hmrcGreen2)
        private set
    var hmrcBlue by mutableStateOf(hmrcBlue)
        private set
    var hmrcTeal by mutableStateOf(hmrcTeal)
        private set
    var hmrcRed by mutableStateOf(hmrcRed)
        private set
    var hmrcGrey1 by mutableStateOf(hmrcGrey1)
        private set
    var hmrcGrey2 by mutableStateOf(hmrcGrey2)
        private set
    var hmrcGrey3 by mutableStateOf(hmrcGrey3)
        private set
    var hmrcPink by mutableStateOf(hmrcPink)
        private set
    var hmrcYellow by mutableStateOf(hmrcYellow)
        private set
    var hmrcErrorText by mutableStateOf(hmrcErrorText)
        private set
    var hmrcDarkText by mutableStateOf(hmrcDarkText)
        private set
    var hmrcLightText by mutableStateOf(hmrcLightText)
        private set
    var hmrcLinkText by mutableStateOf(hmrcLinkText)
        private set
    var hmrcInfoText by mutableStateOf(hmrcInfoText)
        private set
    var hmrcPageBackground by mutableStateOf(hmrcPageBackground)
        private set
    var hmrcDivider by mutableStateOf(hmrcDivider)
        private set
    var hmrcInsetBar by mutableStateOf(hmrcInsetBar)
        private set
    var hmrcInfoMessageWarningHeadlineBackground by mutableStateOf(hmrcInfoMessageWarningHeadlineBackground)
        private set
    var hmrcInfoMessageInfoHeadlineBackground by mutableStateOf(hmrcInfoMessageInfoHeadlineBackground)
        private set
    var hmrcInfoMessageUrgentHeadlineBackground by mutableStateOf(hmrcInfoMessageUrgentHeadlineBackground)
        private set
    var hmrcInfoMessageNoticeHeadlineBackground by mutableStateOf(hmrcInfoMessageNoticeHeadlineBackground)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: HmrcColors) {
        hmrcAlwaysBlack = other.hmrcAlwaysBlack
        hmrcAlwaysWhite = other.hmrcAlwaysWhite
        hmrcBlack = other.hmrcBlack
        hmrcWhite = other.hmrcWhite
        hmrcWhiteBackground = other.hmrcWhiteBackground
        hmrcGreen1 = other.hmrcGreen1
        hmrcGreen2 = other.hmrcGreen2
        hmrcBlue = other.hmrcBlue
        hmrcTeal = other.hmrcTeal
        hmrcRed = other.hmrcRed
        hmrcGrey1 = other.hmrcGrey1
        hmrcGrey2 = other.hmrcGrey2
        hmrcGrey3 = other.hmrcGrey3
        hmrcPink = other.hmrcPink
        hmrcYellow = other.hmrcYellow
        hmrcErrorText = other.hmrcErrorText
        hmrcDarkText = other.hmrcDarkText
        hmrcLightText = other.hmrcLightText
        hmrcLinkText = other.hmrcLinkText
        hmrcInfoText = other.hmrcInfoText
        hmrcPageBackground = other.hmrcPageBackground
        hmrcDivider = other.hmrcDivider
        hmrcInsetBar = other.hmrcInsetBar
        hmrcInfoMessageWarningHeadlineBackground = other.hmrcInfoMessageWarningHeadlineBackground
        hmrcInfoMessageInfoHeadlineBackground = other.hmrcInfoMessageInfoHeadlineBackground
        hmrcInfoMessageUrgentHeadlineBackground = other.hmrcInfoMessageUrgentHeadlineBackground
        hmrcInfoMessageNoticeHeadlineBackground = other.hmrcInfoMessageNoticeHeadlineBackground
        isDark = other.isDark
    }

    fun copy(): HmrcColors = HmrcColors(
        hmrcAlwaysBlack = hmrcAlwaysBlack,
        hmrcAlwaysWhite = hmrcAlwaysWhite,
        hmrcBlack = hmrcBlack,
        hmrcWhite = hmrcWhite,
        hmrcWhiteBackground = hmrcWhiteBackground,
        hmrcGreen1 = hmrcGreen1,
        hmrcGreen2 = hmrcGreen2,
        hmrcBlue = hmrcBlue,
        hmrcTeal = hmrcTeal,
        hmrcRed = hmrcRed,
        hmrcGrey1 = hmrcGrey1,
        hmrcGrey2 = hmrcGrey2,
        hmrcGrey3 = hmrcGrey3,
        hmrcPink = hmrcPink,
        hmrcYellow = hmrcYellow,
        hmrcErrorText = hmrcErrorText,
        hmrcDarkText = hmrcDarkText,
        hmrcLightText = hmrcLightText,
        hmrcLinkText = hmrcLinkText,
        hmrcInfoText = hmrcInfoText,
        hmrcPageBackground = hmrcPageBackground,
        hmrcDivider = hmrcDivider,
        hmrcInsetBar = hmrcInsetBar,
        hmrcInfoMessageWarningHeadlineBackground = hmrcInfoMessageWarningHeadlineBackground,
        hmrcInfoMessageInfoHeadlineBackground = hmrcInfoMessageInfoHeadlineBackground,
        hmrcInfoMessageUrgentHeadlineBackground = hmrcInfoMessageUrgentHeadlineBackground,
        hmrcInfoMessageNoticeHeadlineBackground = hmrcInfoMessageNoticeHeadlineBackground,
        isDark = isDark,
    )
}

@Composable
fun ProvideHmrcColors(colors: HmrcColors, content: @Composable () -> Unit) {
    // Explicitly creating a new object here so we don't mutate the initial [colors] provided,
    // and overwrite the values set in it.
    val colorPalette = remember { colors.copy() }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalHmrcColors provides colorPalette, content = content)
}

private val LocalHmrcColors = staticCompositionLocalOf<HmrcColors> { error("No HmrcColorPalette provided") }
