package uk.gov.hmrc.components.compose.molecule.multiColumnRowView

import android.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

data class MultiColumnRowItem(
    val text: String = "",
    val modifier: Modifier = Modifier,
    val textStyle : TextStyle
)