package uk.gov.hmrc.components.compose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

const val SMALL_RANGE = 360
const val COMPACT_START_RANGE = 361
const val COMPACT_END_RANGE = 480
const val MEDIUM_START_RANGE = 481
const val MEDIUM_END_RANGE = 720

sealed class WindowSize(val size: Int) {
    data class Small(val smallSize: Int) : WindowSize(smallSize)
    data class Compact(val compactSize: Int) : WindowSize(compactSize)
    data class Medium(val mediumSize: Int) : WindowSize(mediumSize)
    data class Large(val largeSize: Int) : WindowSize(largeSize)
}


data class WindowSizeClass(
    val width:WindowSize,
    val height:WindowSize
)

@Composable
fun rememberWindowSizeClass() : WindowSizeClass {
    val config = LocalConfiguration.current

    val width by remember(config){
        mutableStateOf(config.screenWidthDp)
    }

    val height by remember(config) {
        mutableStateOf(config.screenHeightDp)
    }

    val windowWidthClass = when {
        width <= SMALL_RANGE -> WindowSize.Small(width)
        width in COMPACT_START_RANGE..COMPACT_END_RANGE -> WindowSize.Compact(width)
        width in MEDIUM_START_RANGE..MEDIUM_END_RANGE -> WindowSize.Medium(width)
        else -> WindowSize.Large(width)
    }

    val windowHeightClass = when {
        height <= SMALL_RANGE -> WindowSize.Small(height)
        height in COMPACT_START_RANGE..COMPACT_END_RANGE -> WindowSize.Compact(height)
        height in MEDIUM_START_RANGE..MEDIUM_END_RANGE -> WindowSize.Medium(height)
        else -> WindowSize.Large(height)
    }

    return WindowSizeClass(windowWidthClass, windowHeightClass)
}












