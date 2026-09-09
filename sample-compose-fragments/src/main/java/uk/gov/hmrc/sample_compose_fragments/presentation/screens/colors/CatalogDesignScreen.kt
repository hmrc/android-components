package uk.gov.hmrc.sample_compose_fragments.presentation.screens.colors

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.atom.button.SecondaryButton
import uk.gov.hmrc.components.compose.atom.button.SecondaryButtonWithUnderLine
import uk.gov.hmrc.components.compose.atom.text.Text
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors

@Composable
fun CatalogDesignScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            Text(
                text = "HMRC Color Design System",
                style = HmrcTheme.typography.h3
            )
        }
        item {
            TokenSection(
                title = "Buttons",
                tokens = buttonTokens()
            )
        }

        // TEXT

        item {
            TokenSection(
                title = "Text",
                tokens = listOf(
                    ColorTokenItem("Text Primary", colors.hmrcTextPrimary),
                    ColorTokenItem("Text Secondary", colors.hmrcTextSecondary),
                    ColorTokenItem("Link Text", colors.hmrcLinkText),
                    ColorTokenItem("Button Text", colors.hmrcButtonText),
                    ColorTokenItem("Error Text", colors.hmrcErrorText)
                )
            )
        }

        // TILE

        item {
            TokenSection(
                title = "Tile",
                tokens = listOf(
                    ColorTokenItem(
                        "Tile Background",
                        colors.hmrcTileBackgroundColor
                    ),
                    ColorTokenItem(
                        "Tile Heading",
                        colors.hmrcTileHeadingColor
                    ),
                    ColorTokenItem(
                        "Tile Text",
                        colors.hmrcTileTextColor
                    )
                )
            )
        }


        // CARD

        item {
            TokenSection(
                title = "Card",
                tokens = listOf(
                    ColorTokenItem(
                        "Card Background",
                        colors.hmrcCardBackground
                    ),
                    ColorTokenItem(
                        "Card Background Selected",
                        colors.hmrcCardBackgroundSelected
                    ),
                    ColorTokenItem(
                        "Card Text",
                        colors.hmrcCardTextColor
                    )
                )
            )
        }


        item {
            ButtonPreviewSection()
        }
    }
 }

@Composable
fun TokenSection(
    title: String,
    tokens: List<ColorTokenItem>
) {

    Column {

        Text(
            text = title,
            style = HmrcTheme.typography.h4
        )

        Spacer(modifier = Modifier.height(12.dp))

        tokens.forEach {
            ColorTokenRow(token = it)
        }
    }
}

@Composable
fun buttonTokens(): List<ColorTokenItem> {

    return listOf(
        ColorTokenItem(
            "Primary Button Background",
            colors.hmrcPrimaryButtonBackground
        ),
        ColorTokenItem(
            "Primary Button Content",
            colors.hmrcPrimaryButtonContent
        ),
        ColorTokenItem(
            "Primary Button Disabled Background",
            colors.hmrcPrimaryButtonDisabledBackground
        ),
        ColorTokenItem(
            "Primary Button Disabled Content",
            colors.hmrcPrimaryButtonDisabledContent
        ),
        ColorTokenItem(
            "Secondary Button Content",
             colors.hmrcSecondaryButtonContent
        )
    )
}

@Composable
fun ColorTokenRow(
    token: ColorTokenItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(token.color)
                .border(
                    width = 1.dp,
                    color = HmrcTheme.colors.hmrcDivider,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = token.name,
                style = HmrcTheme.typography.body
            )

            Text(
                text = token.color.toHexColor(),
                style = HmrcTheme.typography.h6,
                color = HmrcTheme.colors.hmrcGrey1
            )
        }
    }
}

fun Color.toHexColor(): String {
    return String.format("#%08X", toArgb())
}

@Composable
fun ButtonPreviewSection() {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Button Preview",
            style = HmrcTheme.typography.h4
        )

        PrimaryButton(
            text = "Continue",
            onClick = {}
        )

        PrimaryButton(
            text = "Disabled",
            enabled = false,
            onClick = {}
        )

        SecondaryButton(
            text = "Secondary Button",
            onClick = {}
        )

        SecondaryButtonWithUnderLine(
            text = "Secondary Button Underlined",
            onClick = {}
        )
    }
}

data class ColorTokenItem(
    val name: String,
    val color: Color
)