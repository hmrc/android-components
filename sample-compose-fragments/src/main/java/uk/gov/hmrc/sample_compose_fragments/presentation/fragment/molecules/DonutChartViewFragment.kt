package uk.gov.hmrc.sample_compose_fragments.presentation.fragment.molecules

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentComposeExampleBinding
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules.DonutChartViewScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface

class DonutChartViewFragment : Fragment(R.layout.fragment_compose_example) {

    private lateinit var binding: FragmentComposeExampleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComposeExampleBinding.bind(view)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                HmrcTheme {
                    HmrcSurface {
                        DonutChartViewScreen()
                    }
                }
            }
        }
    }
}