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
import uk.gov.hmrc.components.compose.atom.text.Text
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors

@Composable
fun HmrcColorCatalogScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            Text(
                text = "HMRC Color Design System",
                style = HmrcTheme.typography.h4
            )
        }

        // 1.Background
        item {
            TokenSection(
                title = "Background",
                tokens = listOf(
                    ColorTokenItem(
                        "App Background",
                        colors.hmrcInterface
                    ),
                    ColorTokenItem(
                        "Payment Confirmation Background",
                        colors.hmrcConfirmationBackground
                    )
                )
            )
        }

        // 2. TEXT
        item {
            TokenSection(
                title = "Text",
                tokens = listOf(
                    ColorTokenItem("Text Primary", colors.hmrcStandardTextColor),
                    ColorTokenItem("Link Text", colors.hmrcLinkText),
                    ColorTokenItem("Info Text", colors.hmrcInfoText),
                    ColorTokenItem("Error Text", colors.hmrcErrorTextColor)
                )
            )
        }

        // 3. Card

        item {
            TokenSection(
                title = "Card",
                tokens = listOf(
                    ColorTokenItem(
                        "Card Background",
                        colors.hmrcCardBackground
                    ),
                    ColorTokenItem(
                        "Card Text",
                        colors.hmrcCardTextColor
                    ),
                    ColorTokenItem(
                        "Card Title",
                        colors.hmrcCardTitle
                    )
                )
            )
        }


        // 4. Header

        item {
            TokenSection(
                title = "Header",
                tokens = listOf(
                    ColorTokenItem(
                        "Header Background",
                        colors.hmrcHeaderBackground
                    ),
                    ColorTokenItem(
                        "Header Title Text",
                        colors.hmrcHeaderTitleTextColor
                    ),
                    ColorTokenItem(
                        "Header Navigation Icon",
                        colors.hmrcHeaderNavigationIconColor
                    )
                )
            )
        }

        // 5. Buttons

        item {
            TokenSection(
                title = "Buttons",
                tokens = buttonTokens()
            )
        }

        // 6. OnBoarding Screen
        item {
            TokenSection(
                title = "OnBoarding Screen",
                tokens = listOf(
                    ColorTokenItem("OnBoardingScreen Background", colors.hmrcOnBoardingInterface),
                    ColorTokenItem("OnBoardingScreen Card Background", colors.hmrcOnBoardingCardBackground),
                    ColorTokenItem("OnBoardingScreen Text", colors.hmrcOnBoardingTextColor),
                )
            )
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
            style = HmrcTheme.typography.h5
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
            colors.hmrcPrimaryButtonColor
        ),
        ColorTokenItem(
            "Primary Button Text",
            colors.hmrcPrimaryButtonTextColor
        ),
        ColorTokenItem(
            "Primary Button Disabled Background",
            colors.hmrcPrimaryButtonDisabledColor
        ),
        ColorTokenItem(
            "Primary Button Disabled Text",
            colors.hmrcPrimaryButtonDisabledTextColor
        ),
        ColorTokenItem(
            "Secondary Button Background",
             colors.hmrcSecondaryButtonColor
        ),
        ColorTokenItem(
            "Secondary Button Text",
            colors.hmrcSecondaryButtonTextColor
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
                    color = colors.hmrcDivider,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = token.name,
                style = HmrcTheme.typography.body,
                 color = colors.hmrcStandardTextColor
            )

            Text(
                text = token.color.toHexColor(),
                style = HmrcTheme.typography.h6,
                color = colors.hmrcStandardTextColor
            )
        }
    }
}

fun Color.toHexColor(): String {
    return String.format("#%06X", toArgb() and 0xFFFFFF)
}

data class ColorTokenItem(
    val name: String,
    val color: Color
)