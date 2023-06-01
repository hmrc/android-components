package uk.gov.hmrc.components.compose.molecule.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions

@Composable
fun SwitchRowView(
    title: String?,
    body: String?,
    contentDescription: String? = null,
    onCheckedChangeListener: ((Boolean) -> Unit)? = null,
) {
    var localCheckedState by remember { mutableStateOf(false) }
   // localCheckedState = isChecked

    Row {
        Column {
            title?.let { title ->
                Text(
                    text = title,
                    style = HmrcTheme.typography.h6
                )
            }
            if (title != null && body != null) { Spacer(modifier = Modifier.height(dimensions.hmrcSpacing8)) }
            body?.let { body ->
                Text(
                    text = body,
                    style = HmrcTheme.typography.info
                )
            }
        }
        Switch(
            checked = localCheckedState,
            onCheckedChange = {
                if (onCheckedChangeListener != null) { onCheckedChangeListener(it) }
                localCheckedState = it
            }
        )
    }
}

@Preview
@Composable
fun Preview() {
    SwitchRowView(title = "title", body = "body")
}



// Whole row toggleable

//Row(
//Modifier.toggleable(
//role = Role.Switch,
//value = checkedState,
//onValueChange = { checkedState = it }
//)
//) {
//    Column {
//        Text(text = title)
//        Text(text = body)
//    }
//    Switch(
//        checked = checkedState,
//        onCheckedChange = null
//    )
//}