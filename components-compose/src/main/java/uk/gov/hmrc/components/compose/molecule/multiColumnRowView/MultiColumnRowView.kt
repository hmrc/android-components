package uk.gov.hmrc.components.compose.molecule.multiColumnRowView

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MultiColumnRowView(
    modifier: Modifier = Modifier,
    columnCount : Int = 1,
    values : List<String> = listOf()
) {
    Box(modifier = modifier){

    }
}