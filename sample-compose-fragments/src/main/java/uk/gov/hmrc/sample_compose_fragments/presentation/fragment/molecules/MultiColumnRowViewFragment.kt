package uk.gov.hmrc.sample_compose_fragments.presentation.fragment.molecules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.rememberWindowSizeClass
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules.MultiColumnRowViewScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface

@AndroidEntryPoint
class MultiColumnRowViewFragment : Fragment() {

    private val arg : MultiColumnRowViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val window = rememberWindowSizeClass()
                HmrcTheme(window) {
                    HmrcSurface {
                        MultiColumnRowViewScreen()
                    }
                }
            }
        }
    }
}