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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

private val LightColorPalette = HmrcColors(
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
    hmrcBlack = HmrcBlackDark,
    hmrcWhite = HmrcWhiteDark,
    hmrcWhiteBackground = HmrcWhiteBackgroundDark,
    hmrcGreen1 = HmrcGreen1Dark,
    hmrcGreen2 = HmrcGreen2Dark,
    hmrcBlue = HmrcBlueDark,
    hmrcTeal = HmrcTeal,
    hmrcRed = HmrcRedDark,
    hmrcGrey1 = HmrcGrey1Dark,
    hmrcGrey2 = HmrcGrey2Dark,
    hmrcGrey3 = HmrcGrey3Dark,
    hmrcPink = HmrcPinkDark,
    hmrcYellow = HmrcYellowDark,
    isDark = true
)

@Composable
fun HmrcTheme(
    windowSizeClass: WindowSizeClass,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    // check orientation
    val orientation = when {
        windowSizeClass.width.size > windowSizeClass.height.size -> Orientation.Landscape
        else -> Orientation.Portrait
    }

    val sizeThatMatters = when (orientation) {
        Orientation.Portrait -> windowSizeClass.width
        else -> windowSizeClass.height
    }

    val dimensions = when (sizeThatMatters) {
        is WindowSize.Small -> smallDimensions
        is WindowSize.Medium -> mediumDimensions
        is WindowSize.Compact -> compactDimensions
        else -> largeDimensions
    }

    val hmrcTypography = HmrcTypography(
        hmrcBlack = colors.hmrcBlack,
        hmrcGrey1 = colors.hmrcGrey1,
        hmrcRed = colors.hmrcRed
    )

    // We can define the different typography of different sizes screen
    val typography = when (sizeThatMatters) {
        is WindowSize.Small -> hmrcTypography
        is WindowSize.Medium -> hmrcTypography
        is WindowSize.Compact -> hmrcTypography
        else -> hmrcTypography
    }

    ProvideHmrcTheme(
        dimensions = dimensions,
        orientation = orientation,
        colors = colors,
        typography = typography
    ) {
        MaterialTheme(
            shapes = Shapes,
            content = content
        )
    }
}

object HmrcTheme {
    val colors: HmrcColors
        @Composable
        get() = LocalHmrcColors.current

    val typography: HmrcTypography
        @Composable
        get() = LocalHmrcTypography.current

    val dimensions: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val orientation: Orientation
        @Composable
        get() = LocalOrientationMode.current

    val fonScale: Float
        @Composable
        get() = LocalDensity.current.fontScale
}

object HmrcRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = HmrcTheme.colors.hmrcBlue

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        draggedAlpha = 0.24f,
        focusedAlpha = 0.40f,
        hoveredAlpha = 0.40f,
        pressedAlpha = 0.24f
    )
}

/**
 * HMRC custom Color Palette
 */
@Stable
@SuppressWarnings("LongParameterList")
class HmrcColors(
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
    var hmrcInfoMessageWarningHeadlineBackground by mutableStateOf(
        hmrcInfoMessageWarningHeadlineBackground
    )
        private set
    var hmrcInfoMessageInfoHeadlineBackground by mutableStateOf(
        hmrcInfoMessageInfoHeadlineBackground
    )
        private set
    var hmrcInfoMessageUrgentHeadlineBackground by mutableStateOf(
        hmrcInfoMessageUrgentHeadlineBackground
    )
        private set
    var hmrcInfoMessageNoticeHeadlineBackground by mutableStateOf(
        hmrcInfoMessageNoticeHeadlineBackground
    )
        private set
    var isDark by mutableStateOf(isDark)
        private set

    val hmrcAlwaysBlack: Color = HmrcBlack
    val hmrcAlwaysWhite: Color = HmrcWhite

    fun update(other: HmrcColors) {
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
