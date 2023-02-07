package uk.gov.hmrc.sample_compose_fragments.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

enum class ColorItem(val colorName: String) {
    HmrcBlack("Black"),
    HmrcWhite("White"),
    HmrcGreen1("Green 1"),
    HmrcGreen2("Green 2"),
    HmrcBlue("Blue"),
    HmrcTeal("Teal"),
    HmrcRed("Red"),
    HmrcGrey1("Grey 1"),
    HmrcGrey2("Grey 2"),
    HmrcGrey3("Grey 3"),
    HmrcPink("Pink"),
    HmrcYellow("Yellow"),
    HmrcWhiteBackground("White background"),
    HmrcAlwaysBlack("Always black"),
    HmrcAlwaysWhite("Always white");

    val color: Color
        @Composable
        get() {
            return when (this) {
                HmrcBlack -> HmrcTheme.colors.hmrcBlack
                HmrcWhite -> HmrcTheme.colors.hmrcWhite
                HmrcGreen1 -> HmrcTheme.colors.hmrcGreen1
                HmrcGreen2 -> HmrcTheme.colors.hmrcGreen2
                HmrcBlue -> HmrcTheme.colors.hmrcBlue
                HmrcTeal -> HmrcTheme.colors.hmrcTeal
                HmrcRed -> HmrcTheme.colors.hmrcRed
                HmrcGrey1 -> HmrcTheme.colors.hmrcGrey1
                HmrcGrey2 -> HmrcTheme.colors.hmrcGrey2
                HmrcGrey3 -> HmrcTheme.colors.hmrcGrey3
                HmrcPink -> HmrcTheme.colors.hmrcPink
                HmrcYellow -> HmrcTheme.colors.hmrcYellow
                HmrcWhiteBackground -> HmrcTheme.colors.hmrcWhiteBackground
                HmrcAlwaysBlack -> HmrcTheme.colors.hmrcAlwaysBlack
                HmrcAlwaysWhite -> HmrcTheme.colors.hmrcAlwaysWhite
            }
        }
}