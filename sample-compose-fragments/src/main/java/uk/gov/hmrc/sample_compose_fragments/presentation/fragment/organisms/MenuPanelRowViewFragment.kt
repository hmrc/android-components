package uk.gov.hmrc.sample_compose_fragments.presentation.fragment.organisms


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.rememberWindowSizeClass
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentComposeExampleBinding
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.organisms.MenuPanelRowViewScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface

class MenuPanelRowViewFragment : Fragment(R.layout.fragment_compose_example) {
    private lateinit var binding: FragmentComposeExampleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComposeExampleBinding.bind(view)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val window = rememberWindowSizeClass()
                HmrcTheme(window) {
                    HmrcSurface {
                        MenuPanelRowViewScreen { showToast(getString(R.string.cta_pressed)) }
                    }
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}