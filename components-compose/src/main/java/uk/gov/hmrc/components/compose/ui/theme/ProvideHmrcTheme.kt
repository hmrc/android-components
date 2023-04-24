package uk.gov.hmrc.components.compose.ui.theme

import androidx.compose.runtime.*


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
val LocalOrientationMode = compositionLocalOf { Orientation.Portrait}
