package uk.gov.hmrc.sample_compose_fragments.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentAtomsBinding

class AtomsFragment : Fragment(R.layout.fragment_atoms) {

    private lateinit var binding: FragmentAtomsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAtomsBinding.bind(view)
    }
}