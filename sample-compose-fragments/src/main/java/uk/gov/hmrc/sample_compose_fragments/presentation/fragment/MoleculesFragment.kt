package uk.gov.hmrc.sample_compose_fragments.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentMoleculesBinding

class MoleculesFragment : Fragment(R.layout.fragment_molecules) {

    private lateinit var binding: FragmentMoleculesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoleculesBinding.bind(view)
    }
}