package uk.gov.hmrc.components.compose.organism.summaryrow

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.text.BoldText
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

object SummaryRowView {

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        titleText: String,
        isBoldTitleTextAppearance: Boolean = true,
        titleMaxLines: Int = -1,
        icon: Painter = painterResource(id = R.drawable.components_ic_chevron_right),
        accessibilityMessage: String? = null
    ) {
        Column(modifier = modifier) {
            Title(
                titleText = titleText,
                isBoldTitleTextAppearance = isBoldTitleTextAppearance,
                titleMaxLines = titleMaxLines
            )
        }
    }


    @Composable
    private fun Title(
        titleText: String,
        isBoldTitleTextAppearance: Boolean = true,
        titleMaxLines: Int = -1,
    ) {
        Text(
            text = titleText,
            style = if (isBoldTitleTextAppearance) typography.h6 else typography.body,
            maxLines = if (titleMaxLines == -1) Int.MAX_VALUE else titleMaxLines
        )
    }
}