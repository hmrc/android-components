/*
 * Copyright 2026 HM Revenue & Customs
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
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
@SuppressWarnings("LongParameterList")
class SemanticColors(
//    text
    val hmrcText: Color,

//    Background
    val hmrcInterface: Color,

//    Cards
    val hmrcCardBackground: Color,
//    val hmrcCardBackgroundSelected: Color,
    val hmrcCardTitle: Color,
    val hmrcCardTextColor: Color,

//    Tiles?

//    Header
    val hmrcHeaderBackground: Color,

//    Onboarding
    val hmrcOnboardingBackground: Color,
    val hmrcOnboardingPagerView: Color,

//    Buttons
    val hmrcPrimaryButtonColor: Color,
    val hmrcPrimaryButtonTextColor: Color,
    val hmrcSecondaryButtonColor: Color,
    val hmrcSecondaryButtonTextColor: Color,

//    Navbar
    val hmrcNavBarBackground: Color,

//    Confirmation
    val hmrcConfirmationBackground: Color,
)

val LightSemanticColors = SemanticColors(
    hmrcText = HmrcBlack,
    hmrcInterface = HmrcLightGrey,
    hmrcCardBackground = HmrcWhite,
    hmrcCardTitle = HmrcBlue,
    hmrcCardTextColor = HmrcBlack,
    hmrcHeaderBackground = HmrcBlue,
    hmrcOnboardingBackground = HmrcGrey3,
    hmrcOnboardingPagerView = HmrcWhite,
    hmrcPrimaryButtonColor = HmrcGreen1,
    hmrcPrimaryButtonTextColor = HmrcWhite,
    hmrcSecondaryButtonColor = HmrcTransparent,
    hmrcSecondaryButtonTextColor = HmrcBlue,
    hmrcNavBarBackground = HmrcWhite,
    hmrcConfirmationBackground = HmrcGreen1,
)

val DarkSemanticColors = SemanticColors(
    hmrcText = HmrcWhite,
    hmrcInterface = HmrcDarkNavy2,
    hmrcCardBackground = HmrcDarkNavy3,
    hmrcCardTitle = HmrcWhite,
    hmrcCardTextColor = HmrcWhite,
    hmrcHeaderBackground = HmrcNavy,
    hmrcOnboardingBackground = HmrcDarkNavy2,
    hmrcOnboardingPagerView = HmrcDarkNavy3,
    hmrcPrimaryButtonColor = HmrcPrimaryGreen,
    hmrcPrimaryButtonTextColor = HmrcWhite,
    hmrcSecondaryButtonColor = HmrcTransparent,
    hmrcSecondaryButtonTextColor = HmrcWhite,
    hmrcNavBarBackground = HmrcDarkNavy3,
    hmrcConfirmationBackground = HmrcPrimaryGreen,
)
