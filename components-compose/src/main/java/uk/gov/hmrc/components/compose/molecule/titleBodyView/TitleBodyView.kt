package uk.gov.hmrc.components.compose.molecule.titleBodyView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import uk.gov.hmrc.components.compose.atom.heading.Heading4
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_8

@Composable
fun H4TitleBodyView(
    H4Text: String,
    H5Text: String,
    rowModifier: Modifier = Modifier,
    ) {
    Column {
        Row(modifier = rowModifier) {
            Heading4(
                text = H4Text,
            )
        }
        Row(modifier = Modifier.padding(top = hmrc_spacing_16)) {
            Heading5(
                text = H5Text,
            )
        }
    }
}


@Composable
fun H5TitleBodyView(
    H5Text: String,
    bodyText: String,
    rowModifier: Modifier = Modifier,
) {
    Column {
        Row(modifier = rowModifier) {
            Heading5(
                text = H5Text,
            )
            Text(
                text = bodyText,
                modifier = Modifier.padding(top = hmrc_spacing_16),
            )
        }
    }
}

@Composable
fun BoldTitleBodyView(
    headingText: String,
    bodyText: String,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(modifier = modifier) {
            Text(
                modifier = modifier,
                text = headingText,
                style = HmrcTheme.typography.h6,
            )
            Text(
                text = bodyText,
                modifier = Modifier.padding(top = hmrc_spacing_8),
            )
        }
    }
}