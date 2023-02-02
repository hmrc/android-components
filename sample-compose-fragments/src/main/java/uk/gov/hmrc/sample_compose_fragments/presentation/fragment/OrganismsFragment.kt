package uk.gov.hmrc.sample_compose_fragments.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentOrganismsBinding

class OrganismsFragment : Fragment(R.layout.fragment_organisms) {

    private lateinit var binding: FragmentOrganismsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrganismsBinding.bind(view)
    }
}