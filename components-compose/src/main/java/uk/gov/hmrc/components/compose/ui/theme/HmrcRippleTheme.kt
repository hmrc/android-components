package uk.gov.hmrc.components.compose.ui.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmrcRippleTheme(): RippleConfiguration {
    return RippleConfiguration(
        color = HmrcTheme.colors.hmrcBlue,
        rippleAlpha = RippleAlpha(
            draggedAlpha = 0.24f,
            focusedAlpha = 0.40f,
            hoveredAlpha = 0.40f,
            pressedAlpha = 0.24f
        )
    )
}
