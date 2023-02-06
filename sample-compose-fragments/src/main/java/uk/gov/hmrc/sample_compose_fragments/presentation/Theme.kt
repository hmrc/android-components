package uk.gov.hmrc.sample_compose_fragments.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey3
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhite


val lightThemeColors = lightColorScheme(
    primary = HmrcGrey3,
    inverseOnSurface = HmrcWhite,
    onSurface = HmrcBlack,
)

val darkThemeColors = darkColorScheme(
    primary = HmrcBlack,
    onSurface = HmrcWhite
)

@SuppressWarnings
@Composable
fun SampleComposeFragmentsTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) {
        darkThemeColors
    } else {
        lightThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}