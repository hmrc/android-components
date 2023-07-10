package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcCard
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

object SelectRowViewScreen {

    private const val THIRD_ROW_VIEW = 3

    @Composable
    operator fun invoke() {
        val context = LocalContext.current
        ScreenScrollViewColumn {
            PlaceholderSlot {
                SelectRowView(
                    selectRowViewItems = listOf("(Body) Description"),
                    errorText = "This is an error"
                ) { position, value ->
                    // Handle the SelectRowView item click listener
                }
            }

            ExamplesSlot {
                //Example one
                HmrcCard(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    val errorText = remember { mutableStateOf("") }
                    SelectRowView(
                        selectRowViewItems = listOf(
                            "First row",
                            "Second row",
                            "Show an error",
                            stringResource(id = R.string.longer_text)
                        ),
                        errorText = errorText.value
                    ) { position, value ->
                        if (position == THIRD_ROW_VIEW - 1) {
                            errorText.value = "This is an error"
                        }
                        Toast.makeText(
                            context,
                            "Position: $position selected\nValue: $value",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                //Example two
                HmrcCard(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)) {
                    SelectRowView(
                        selectRowViewItems = listOf(
                            "First row",
                            "Second row",
                            "Third row",
                            "Fourth row"
                        ),
                        checkedIcon = R.drawable.components_select_row_tick_checked
                    ) { position, value ->
                        // We can use this block for Select Row View click listener
                    }
                }
            }
        }
    }
}