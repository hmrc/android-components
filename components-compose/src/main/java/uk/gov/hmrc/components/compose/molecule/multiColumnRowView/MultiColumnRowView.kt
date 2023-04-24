package uk.gov.hmrc.components.compose.molecule.multiColumnRowView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MultiColumnRowView(
    modifier: Modifier = Modifier,
    columnList: List<MultiColumnRowItem> = listOf()
) {
    Box(modifier = modifier) {
        Row {
            columnList.forEach { column ->
                Text(text = column.text, style = column.textStyle, modifier = column.modifier)
            }
        }
    }
}
