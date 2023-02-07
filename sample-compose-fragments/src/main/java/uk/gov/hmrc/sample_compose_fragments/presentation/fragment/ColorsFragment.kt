package uk.gov.hmrc.sample_compose_fragments.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentColorsBinding
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ColorsListScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.ColorsViewModel

@AndroidEntryPoint
class ColorsFragment : Fragment(R.layout.fragment_colors) {

    private lateinit var binding: FragmentColorsBinding
    private val viewModel: ColorsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentColorsBinding.bind(view)
        binding.composeView.setContent {
            HmrcTheme {
                Surface(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    color = HmrcTheme.colors.hmrcPageBackground
                ) {
                    ColorsListScreen(viewModel = viewModel)
                }
            }
        }
    }
}