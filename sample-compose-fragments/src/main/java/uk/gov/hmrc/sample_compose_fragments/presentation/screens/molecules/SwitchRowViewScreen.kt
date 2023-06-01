package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.gov.hmrc.components.compose.molecule.item.SwitchRowView
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.SwitchRowViewModel

@Composable
fun SwitchRowViewScreen() {

    val viewModel = viewModel<SwitchRowViewModel>()
    val context = LocalContext.current

    ScreenScrollViewColumn {
        PlaceholderSlot {
            SwitchRowView(
                title = stringResource(R.string.switch_row_placeholder_title),
                body = stringResource(viewModel.placeholderBodyText.collectAsStateWithLifecycle().value),
                onCheckedChangeListener = { enabled ->
                    viewModel.onPlaceholderToggleChanged(enabled)
                    Toast.makeText(context, "Placeholder state: $enabled", Toast.LENGTH_SHORT).show()
                },
            )
        }

        ExamplesSlot {

        }
    }
}
