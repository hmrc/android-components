package uk.gov.hmrc.sample_compose_fragments.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ColorsListScreen

class ColorsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ColorsListScreen()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ColorsFragment()
    }
}