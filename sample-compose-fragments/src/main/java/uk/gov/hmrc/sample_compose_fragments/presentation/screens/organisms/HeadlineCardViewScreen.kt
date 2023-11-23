package uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.organism.headline.HeadlineCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object HeadlineCardViewScreen {
    @Composable
    operator fun invoke() {
        ScreenScrollViewColumn {
            PlaceholderSlot {
                HeadlineCardView(
                    modifier = Modifier.background(HmrcTheme.colors.hmrcWhite),
                    title = "Title",
                    headline = "Headline",
                    views = listOf(
                        HeadlineCardView.ComposableItem(
                            content = { Text(text = "Hello") },
                            onClick = { println("Clicked Hello") }
                        ),
                        HeadlineCardView.ComposableItem(
                            content = {
                                PrimaryButton(
                                    text = "Hello",
                                    onClick = {},
                                )
                            },
                            onClick = { println("Clicked Hello") }
                        )
                    )
                )
            }

            ExamplesSlot {
                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        modifier = Modifier.background(HmrcTheme.colors.hmrcWhite),
                        title = "Title",
                        headline = "Headline",
                        views = listOf(
                            HeadlineCardView.ComposableItem(
                                content = { Text(text = "Hello") },
                                onClick = { println("Clicked Hello") }
                            ),
                            HeadlineCardView.ComposableItem(
                                content = {
                                    PrimaryButton(
                                        text = "Hello",
                                        onClick = {},
                                    )
                                },
                                onClick = { println("Clicked Hello") }
                            )
                        )
                    )
                }

                HmrcCardView(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    HeadlineCardView(
                        modifier = Modifier.background(HmrcTheme.colors.hmrcWhite),
                        title = "Title",
                        headline = "Headline",
                        views = listOf(
                            HeadlineCardView.ComposableItem(
                                content = { Text(text = "Hello") },
                                onClick = { println("Clicked Hello") }
                            ),
                            HeadlineCardView.ComposableItem(
                                content = {
                                    PrimaryButton(
                                        text = "Hello",
                                        onClick = {},
                                    )
                                },
                                onClick = { println("Clicked Hello") }
                            )
                        )
                    )
                }
            }
        }
    }
}
