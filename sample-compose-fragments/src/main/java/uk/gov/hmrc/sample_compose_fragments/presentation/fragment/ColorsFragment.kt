package uk.gov.hmrc.sample_compose_fragments.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.sample_compose_components.databinding.FragmentColorsBinding
import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ColorsListScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.ColorsViewModel

@AndroidEntryPoint
class ColorsFragment : Fragment() {
    private lateinit var binding: FragmentColorsBinding
    private val viewModel: ColorsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            ColorsListScreen(viewModel, navigateTo = {
            })
        }
    }
}

typealias ColorItemClickListener = (item: ColorItem) -> Unit