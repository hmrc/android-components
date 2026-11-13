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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
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
    hmrcGreen1 = HmrcGreen1,
    hmrcBlue = HmrcBlue,
    hmrcTeal = HmrcTeal,
    hmrcGrey1 = HmrcGrey1,
    hmrcGrey2 = HmrcGrey2,
    hmrcGrey3 = HmrcGrey3,
    hmrcPink = HmrcPink,
    hmrcYellow = HmrcYellow,
    isDark = false,
    hmrcTransparent = HmrcTransparent,
    // App Background
    hmrcInterface = HmrcLightGrey,
    hmrcConfirmationBackground = HmrcGreen1,

    // cards
    hmrcCardBackground = HmrcWhite,
    hmrcCardTitle = HmrcBlue,
    hmrcCardTextColor = HmrcBlack,
    hmrcCardPressedColor = HmrcBlue,

    // Header
    hmrcHeaderBackground = HmrcBlue,
    hmrcHeaderTitleTextColor = HmrcWhite,
    hmrcHeaderNavigationIconColor = HmrcWhite,

    // Text
    hmrcStandardTextColor = HmrcBlack,

    // CTA
    hmrcPrimaryButtonColor = HmrcGreen1,
    hmrcPrimaryButtonTextColor = HmrcWhite,
    hmrcSecondaryButtonColor = HmrcTransparent,
    hmrcSecondaryButtonTextColor = HmrcBlue,
    hmrcSecondaryButtonRippleBackground = HmrcBlue,
    hmrcPrimaryButtonDisabledColor = HmrcGrey2,
    hmrcPrimaryButtonDisabledTextColor = HmrcGrey1,

    // Icon
    hmrcIconTint = HmrcBlack,
    hmrcPrePostBox = HmrcGrey3,

    // Errors
    hmrcErrorTextColor = HmrcRed,

    // Onboarding Screens
    hmrcOnBoardingInterface = HmrcLightGrey,
    hmrcOnBoardingCardBackground = HmrcWhite,
    hmrcViewPagerIconSelected = HmrcBlue,
    hmrcViewPagerIconNotSelected = HmrcGrey2,
    hmrcOnBoardingTextColor = HmrcBlack,

    // Segmented Button
    selectedSegmentedTab = HmrcLightBlue,
    pressedSegmentedTab = HmrcLightPressedBlue,
    inactiveSegmentedTab = HmrcTransparent,
    textColorSegmentedTab = HmrcBlack,

    // Tab Bar
    tabBarBackground = HmrcWhite,
    tabBarSelectedText = HmrcBlue,
    tabBarUnselectedText = HmrcGrey1,
    tabBarBorderSelected = HmrcBlue,

    // Donuts
    hmrcDonutChartColor1 = HmrcTeal,
    hmrcDonutChartColor2 = HmrcDonutBlue,
    hmrcDonutChartColor3 = HmrcDonutBlue,
)

private val DarkColorPalette = HmrcColors(
    hmrcBlack = HmrcBlackDark,
    hmrcWhite = HmrcWhiteDark,
    hmrcGreen1 = HmrcGreen1Dark,
    hmrcBlue = HmrcBlueDark,
    hmrcTeal = HmrcTeal,
    hmrcGrey1 = HmrcGrey1Dark,
    hmrcGrey2 = HmrcGrey2Dark,
    hmrcGrey3 = HmrcGrey3Dark,
    hmrcPink = HmrcPinkDark,
    hmrcYellow = HmrcYellowDark,
    isDark = true,
    hmrcTransparent = HmrcTransparent,

    // App Background
    hmrcInterface = HmrcDarkNavy2,
    hmrcConfirmationBackground = HmrcPrimaryGreen,

    // cards
    hmrcCardBackground = HmrcDarkNavy3,
    hmrcCardTitle = HmrcWhite,
    hmrcCardTextColor = HmrcWhite,
    hmrcCardPressedColor = HmrcLavender,

    // Header
    hmrcHeaderBackground = HmrcNavy,
    hmrcHeaderTitleTextColor = HmrcWhite,
    hmrcHeaderNavigationIconColor = HmrcWhite,

    // Text
    hmrcStandardTextColor = HmrcWhite,

    // CTA
    hmrcPrimaryButtonColor = HmrcPrimaryGreen,
    hmrcPrimaryButtonTextColor = HmrcWhite,
    hmrcSecondaryButtonColor = HmrcTransparent,
    hmrcSecondaryButtonTextColor = HmrcPrimaryTeal,
    hmrcSecondaryButtonRippleBackground = HmrcBlack,
    hmrcPrimaryButtonDisabledColor = HmrcGrey4,
    hmrcPrimaryButtonDisabledTextColor = HmrcGrey2,

    // Icon
    hmrcIconTint = HmrcMidGrey,
    hmrcPrePostBox = HmrcDarkBlue,

    // Errors
    hmrcErrorTextColor = HmrcPrimaryRed,

    // Onboarding Screens
    hmrcOnBoardingInterface = HmrcDarkNavy2,
    hmrcOnBoardingCardBackground = HmrcDarkNavy3,
    hmrcViewPagerIconSelected = HmrcPrimaryTeal,
    hmrcViewPagerIconNotSelected = HmrcGrey4,
    hmrcOnBoardingTextColor = HmrcWhite,

    // Segmented Button
    selectedSegmentedTab = HmrcNavyBlue,
    pressedSegmentedTab = HmrcCyanBlue,
    inactiveSegmentedTab = HmrcTransparent,
    textColorSegmentedTab = HmrcWhite,

    // Tab Bar
    tabBarBackground = HmrcDarkNavy3,
    tabBarSelectedText = HmrcPrimaryTeal,
    tabBarUnselectedText = HmrcPrimaryTeal,
    tabBarBorderSelected = HmrcPrimaryTeal,

    // Donuts
    hmrcDonutChartColor1 = HmrcWhite,
    hmrcDonutChartColor2 = HmrcTeal,
    hmrcDonutChartColor3 = HmrcTeal,
)

@Composable
fun HmrcTheme(
    windowSizeClass: WindowSizeClass = rememberWindowSizeClass(),
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
        hmrcStandardTextColor = colors.hmrcStandardTextColor,
        hmrcWhite = colors.hmrcWhite,
        hmrcGrey1 = colors.hmrcGrey1,
        hmrcBlue = colors.hmrcBlue,
        hmrcErrorTextColor = colors.hmrcErrorTextColor,
    )

    // We can define the different typography of different sizes screen
    val typography = when (sizeThatMatters) {
        is WindowSize.Small -> hmrcTypography
        is WindowSize.Medium -> hmrcTypography
        is WindowSize.Compact -> hmrcTypography
        else -> hmrcTypography
    }

    val textInputViewColors = textInputViewColors(colors = colors)

    ProvideHmrcTheme(
        dimensions = dimensions,
        orientation = orientation,
        colors = colors,
        textFieldColors = textInputViewColors,
        typography = typography
    ) {
        MaterialTheme(
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun textInputViewColors(colors: HmrcColors) = OutlinedTextFieldDefaults.colors(
    focusedTextColor = colors.hmrcBlack,
    unfocusedTextColor = colors.hmrcBlack,
    errorTextColor = colors.hmrcErrorTextColor,
    errorLeadingIconColor = colors.hmrcBlack,
    focusedTrailingIconColor = colors.hmrcBlack,
    unfocusedTrailingIconColor = colors.hmrcBlack,
    errorTrailingIconColor = colors.hmrcBlack,
    focusedLabelColor = colors.hmrcBlue,
    unfocusedLabelColor = colors.hmrcBlack,
    errorLabelColor = colors.hmrcErrorTextColor,
    focusedBorderColor = colors.hmrcBlue,
    errorBorderColor = colors.hmrcBlack,
    unfocusedBorderColor = colors.hmrcBlack,
    focusedPlaceholderColor = colors.hmrcGrey1,
    unfocusedPlaceholderColor = colors.hmrcGrey1,
    errorPlaceholderColor = colors.hmrcGrey1,
    errorSupportingTextColor = colors.hmrcErrorTextColor,
    focusedSupportingTextColor = colors.hmrcBlack,
    cursorColor = colors.hmrcBlue,
    errorCursorColor = colors.hmrcErrorTextColor,
)

object HmrcTheme {
    val colors: HmrcColors
        @Composable
        get() = LocalHmrcColors.current

    val textFieldColors
        @Composable
        get() = LocalTextFieldColors.current

    val typography: HmrcTypography
        @Composable
        get() = LocalHmrcTypography.current

    val dimensions: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val orientation: Orientation
        @Composable
        get() = LocalOrientationMode.current

    val fontScale: Float
        @Composable
        get() = LocalDensity.current.fontScale
}

/**
 * HMRC custom Color Palette
 */
@Stable
@SuppressWarnings("LongParameterList")
class HmrcColors(
    hmrcBlack: Color,
    hmrcWhite: Color,
    hmrcGreen1: Color,
    hmrcBlue: Color,
    hmrcTeal: Color,
    hmrcGrey1: Color,
    hmrcGrey2: Color,
    hmrcGrey3: Color,
    hmrcPink: Color,
    hmrcYellow: Color,
    hmrcDarkText: Color = hmrcBlack,
    hmrcLightText: Color = hmrcWhite,
    hmrcLinkText: Color = hmrcBlue,
    hmrcInfoText: Color = hmrcGrey1,
    hmrcPageBackground: Color = hmrcGrey3,
    hmrcDivider: Color = hmrcGrey2,
    hmrcInsetBar: Color = hmrcGrey2,
    hmrcDonutChartColor1: Color,
    hmrcDonutChartColor2: Color,
    hmrcDonutChartColor3: Color,
    isDark: Boolean,
    hmrcTransparent: Color,
    hmrcCardBackground: Color,
    hmrcCardTitle: Color,
    hmrcCardTextColor: Color,
    hmrcCardPressedColor: Color,
    hmrcInterface: Color,
    hmrcHeaderBackground: Color,
    hmrcHeaderTitleTextColor: Color,
    hmrcHeaderNavigationIconColor: Color,
    hmrcPrimaryButtonColor: Color,
    hmrcPrimaryButtonTextColor: Color,
    hmrcSecondaryButtonColor: Color,
    hmrcSecondaryButtonTextColor: Color,
    hmrcSecondaryButtonRippleBackground: Color,
    hmrcPrimaryButtonDisabledColor: Color,
    hmrcPrimaryButtonDisabledTextColor: Color,
    hmrcConfirmationBackground: Color,
    hmrcIconTint: Color,
    hmrcErrorTextColor: Color,
    hmrcStandardTextColor: Color,
    hmrcPrePostBox: Color,
    hmrcOnBoardingInterface: Color,
    hmrcOnBoardingCardBackground: Color,
    hmrcViewPagerIconSelected: Color,
    hmrcViewPagerIconNotSelected: Color,
    hmrcOnBoardingTextColor: Color,
    selectedSegmentedTab: Color,
    pressedSegmentedTab: Color,
    inactiveSegmentedTab: Color,
    textColorSegmentedTab: Color,
    tabBarBackground: Color,
    tabBarSelectedText: Color,
    tabBarUnselectedText: Color,
    tabBarBorderSelected: Color,
) {
    var hmrcBlack by mutableStateOf(hmrcBlack)
        private set
    var hmrcWhite by mutableStateOf(hmrcWhite)
        private set
    var hmrcGreen1 by mutableStateOf(hmrcGreen1)
        private set
    var hmrcBlue by mutableStateOf(hmrcBlue)
        private set
    var hmrcTeal by mutableStateOf(hmrcTeal)
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
    var hmrcDonutChartColor1 by mutableStateOf(hmrcDonutChartColor1)
        private set
    var hmrcDonutChartColor2 by mutableStateOf(hmrcDonutChartColor2)
        private set
    var hmrcDonutChartColor3 by mutableStateOf(hmrcDonutChartColor3)
        private set
    var isDark by mutableStateOf(isDark)
        private set
    var hmrcCardBackground by mutableStateOf(hmrcCardBackground)
        private set
    var hmrcCardTitle by mutableStateOf(hmrcCardTitle)
        private set
    var hmrcCardTextColor by mutableStateOf(hmrcCardTextColor)
        private set
    var hmrcCardPressedColor by mutableStateOf(hmrcCardPressedColor)
        private set

    val hmrcAlwaysBlack: Color = HmrcBlack
    val hmrcAlwaysWhite: Color = HmrcWhite

    var hmrcTransparent by mutableStateOf(hmrcTransparent)
        private set

    var hmrcInterface by mutableStateOf(hmrcInterface)
        private set

    var hmrcHeaderBackground by mutableStateOf(hmrcHeaderBackground)
        private set

    var hmrcHeaderTitleTextColor by mutableStateOf(hmrcHeaderTitleTextColor)
        private set

    var hmrcHeaderNavigationIconColor by mutableStateOf(hmrcHeaderNavigationIconColor)
        private set

    var hmrcConfirmationBackground by mutableStateOf(hmrcConfirmationBackground)
        private set
    var hmrcPrimaryButtonColor by mutableStateOf(hmrcPrimaryButtonColor)
        private set
    var hmrcPrimaryButtonTextColor by mutableStateOf(hmrcPrimaryButtonTextColor)
        private set
    var hmrcSecondaryButtonColor by mutableStateOf(hmrcSecondaryButtonColor)
        private set
    var hmrcSecondaryButtonTextColor by mutableStateOf(hmrcSecondaryButtonTextColor)
        private set
    var hmrcSecondaryButtonRippleBackground by mutableStateOf(hmrcSecondaryButtonRippleBackground)
        private set
    var hmrcPrimaryButtonDisabledColor by mutableStateOf(hmrcPrimaryButtonDisabledColor)
        private set
    var hmrcPrimaryButtonDisabledTextColor by mutableStateOf(hmrcPrimaryButtonDisabledTextColor)
        private set
    var hmrcIconTint by mutableStateOf(hmrcIconTint)
        private set
    var hmrcErrorTextColor by mutableStateOf(hmrcErrorTextColor)
        private set

    var hmrcStandardTextColor by mutableStateOf(hmrcStandardTextColor)
        private set
    var hmrcPrePostBox by mutableStateOf(hmrcPrePostBox)
        private set
    var hmrcOnBoardingInterface by mutableStateOf(hmrcOnBoardingInterface)
        private set
    var hmrcOnBoardingCardBackground by mutableStateOf(hmrcOnBoardingCardBackground)
        private set
    var hmrcViewPagerIconSelected by mutableStateOf(hmrcViewPagerIconSelected)
        private set
    var hmrcViewPagerIconNotSelected by mutableStateOf(hmrcViewPagerIconNotSelected)
        private set
    var hmrcOnBoardingTextColor by mutableStateOf(hmrcOnBoardingTextColor)
        private set
    var selectedSegmentedTab by mutableStateOf(selectedSegmentedTab)
        private set
    var pressedSegmentedTab by mutableStateOf(pressedSegmentedTab)
        private set
    var inactiveSegmentedTab by mutableStateOf(inactiveSegmentedTab)
        private set
    var textColorSegmentedTab by mutableStateOf(textColorSegmentedTab)
        private set
    var tabBarBackground by mutableStateOf(tabBarBackground)
        private set
    var tabBarSelectedText by mutableStateOf(tabBarSelectedText)
        private set
    var tabBarUnselectedText by mutableStateOf(tabBarUnselectedText)
        private set
    var tabBarBorderSelected by mutableStateOf(tabBarBorderSelected)
        private set

    @Suppress("LongMethod")
    fun update(other: HmrcColors) {
        hmrcBlack = other.hmrcBlack
        hmrcWhite = other.hmrcWhite
        hmrcGreen1 = other.hmrcGreen1
        hmrcBlue = other.hmrcBlue
        hmrcTeal = other.hmrcTeal
        hmrcGrey1 = other.hmrcGrey1
        hmrcGrey2 = other.hmrcGrey2
        hmrcGrey3 = other.hmrcGrey3
        hmrcPink = other.hmrcPink
        hmrcYellow = other.hmrcYellow
        hmrcDarkText = other.hmrcDarkText
        hmrcLightText = other.hmrcLightText
        hmrcLinkText = other.hmrcLinkText
        hmrcInfoText = other.hmrcInfoText
        hmrcPageBackground = other.hmrcPageBackground
        hmrcDivider = other.hmrcDivider
        hmrcInsetBar = other.hmrcInsetBar
        hmrcDonutChartColor1 = other.hmrcDonutChartColor1
        hmrcDonutChartColor2 = other.hmrcDonutChartColor2
        hmrcDonutChartColor3 = other.hmrcDonutChartColor3
        isDark = other.isDark
        hmrcCardBackground = other.hmrcCardBackground
        hmrcCardTitle = other.hmrcCardTitle
        hmrcCardTextColor = other.hmrcCardTextColor
        hmrcCardPressedColor = other.hmrcCardPressedColor
        hmrcInterface = other.hmrcInterface
        hmrcHeaderBackground = other.hmrcHeaderBackground
        hmrcHeaderTitleTextColor = other.hmrcHeaderTitleTextColor
        hmrcHeaderNavigationIconColor = other.hmrcHeaderNavigationIconColor
        hmrcInterface = other.hmrcInterface
        hmrcConfirmationBackground = other.hmrcConfirmationBackground
        hmrcPrimaryButtonColor = other.hmrcPrimaryButtonColor
        hmrcPrimaryButtonTextColor = other.hmrcPrimaryButtonTextColor
        hmrcSecondaryButtonColor = other.hmrcSecondaryButtonColor
        hmrcSecondaryButtonTextColor = other.hmrcSecondaryButtonTextColor
        hmrcSecondaryButtonRippleBackground = other.hmrcSecondaryButtonRippleBackground
        hmrcPrimaryButtonDisabledColor = other.hmrcPrimaryButtonDisabledColor
        hmrcPrimaryButtonDisabledTextColor = other.hmrcPrimaryButtonDisabledTextColor
        hmrcIconTint = other.hmrcIconTint
        hmrcErrorTextColor = other.hmrcErrorTextColor
        hmrcStandardTextColor = other.hmrcStandardTextColor
        hmrcPrePostBox = other.hmrcPrePostBox
        hmrcOnBoardingInterface = other.hmrcOnBoardingInterface
        hmrcOnBoardingCardBackground = other.hmrcOnBoardingCardBackground
        hmrcViewPagerIconSelected = other.hmrcViewPagerIconSelected
        hmrcViewPagerIconNotSelected = hmrcViewPagerIconNotSelected
        hmrcOnBoardingTextColor = other.hmrcOnBoardingTextColor
        selectedSegmentedTab = other.selectedSegmentedTab
        pressedSegmentedTab = other.pressedSegmentedTab
        inactiveSegmentedTab = other.inactiveSegmentedTab
        textColorSegmentedTab = other.textColorSegmentedTab
        tabBarBackground = other.tabBarBackground
        tabBarSelectedText = other.tabBarSelectedText
        tabBarUnselectedText = other.tabBarUnselectedText
        tabBarBorderSelected = other.tabBarBorderSelected
    }

    @Suppress("LongMethod")
    fun copy(): HmrcColors = HmrcColors(
        hmrcBlack = hmrcBlack,
        hmrcWhite = hmrcWhite,
        hmrcGreen1 = hmrcGreen1,
        hmrcBlue = hmrcBlue,
        hmrcTeal = hmrcTeal,
        hmrcGrey1 = hmrcGrey1,
        hmrcGrey2 = hmrcGrey2,
        hmrcGrey3 = hmrcGrey3,
        hmrcPink = hmrcPink,
        hmrcYellow = hmrcYellow,
        hmrcDarkText = hmrcDarkText,
        hmrcLightText = hmrcLightText,
        hmrcLinkText = hmrcLinkText,
        hmrcInfoText = hmrcInfoText,
        hmrcPageBackground = hmrcPageBackground,
        hmrcDivider = hmrcDivider,
        hmrcInsetBar = hmrcInsetBar,
        hmrcDonutChartColor1 = hmrcDonutChartColor1,
        hmrcDonutChartColor2 = hmrcDonutChartColor2,
        hmrcDonutChartColor3 = hmrcDonutChartColor3,
        isDark = isDark,
        hmrcTransparent = hmrcTransparent,
        hmrcCardBackground = hmrcCardBackground,
        hmrcCardTitle = hmrcCardTitle,
        hmrcCardTextColor = hmrcCardTextColor,
        hmrcCardPressedColor = hmrcCardPressedColor,
        hmrcInterface = hmrcInterface,
        hmrcHeaderBackground = hmrcHeaderBackground,
        hmrcHeaderTitleTextColor = hmrcHeaderTitleTextColor,
        hmrcHeaderNavigationIconColor = hmrcHeaderNavigationIconColor,
        hmrcConfirmationBackground = hmrcConfirmationBackground,
        hmrcPrimaryButtonColor = hmrcPrimaryButtonColor,
        hmrcPrimaryButtonTextColor = hmrcPrimaryButtonTextColor,
        hmrcSecondaryButtonColor = hmrcSecondaryButtonColor,
        hmrcSecondaryButtonTextColor = hmrcSecondaryButtonTextColor,
        hmrcSecondaryButtonRippleBackground = hmrcSecondaryButtonRippleBackground,
        hmrcPrimaryButtonDisabledColor = hmrcPrimaryButtonDisabledColor,
        hmrcPrimaryButtonDisabledTextColor = hmrcPrimaryButtonDisabledTextColor,
        hmrcIconTint = hmrcIconTint,
        hmrcErrorTextColor = hmrcErrorTextColor,
        hmrcStandardTextColor = hmrcStandardTextColor,
        hmrcPrePostBox = hmrcPrePostBox,
        hmrcOnBoardingInterface = hmrcOnBoardingInterface,
        hmrcOnBoardingCardBackground = hmrcOnBoardingCardBackground,
        hmrcViewPagerIconSelected = hmrcViewPagerIconSelected,
        hmrcViewPagerIconNotSelected = hmrcViewPagerIconNotSelected,
        hmrcOnBoardingTextColor = hmrcOnBoardingTextColor,
        selectedSegmentedTab = selectedSegmentedTab,
        pressedSegmentedTab = pressedSegmentedTab,
        inactiveSegmentedTab = inactiveSegmentedTab,
        textColorSegmentedTab = textColorSegmentedTab,
        tabBarBackground = tabBarBackground,
        tabBarSelectedText = tabBarSelectedText,
        tabBarUnselectedText = tabBarUnselectedText,
        tabBarBorderSelected = tabBarBorderSelected,
    )
}
