/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.sample_compose_fragments.presentation.fragment.molecules

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentMoleculesBinding
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.MOLECULE_BOLD_TITLE_BODY_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.MOLECULE_H4_TITLE_BODY_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.MOLECULE_H5_TITLE_BODY_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.MOLECULE_INSET_TEXT_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.MOLECULE_INSET_VIEW
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ComponentListScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.MoleculesViewModel

@AndroidEntryPoint
class MoleculesFragment : Fragment(R.layout.fragment_molecules) {

    private lateinit var binding: FragmentMoleculesBinding
    private val viewModel: MoleculesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoleculesBinding.bind(view)
        binding.composeViewMolecules.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val listItems by viewModel.moleculesItems.collectAsState()
                HmrcTheme {
                    Surface(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                        color = colors.hmrcPageBackground
                    ) {
                        ComponentListScreen(items = listItems, navigateTo = {
                            when (it.id) {
                                MOLECULE_INSET_VIEW -> {
                                    findNavController().navigate(R.id.action_moleculesFragment_to_insetViewFragment)
                                }
                                MOLECULE_INSET_TEXT_VIEW -> {
                                    findNavController().navigate(R.id.action_moleculesFragment_to_insetTextViewFragment)
                                }
                                MOLECULE_BOLD_TITLE_BODY_VIEW -> {
                                    findNavController().navigate(R.id.action_moleculesFragment_to_boldTitleBodyViewFragment)
                                }
                                MOLECULE_H4_TITLE_BODY_VIEW -> {
                                    findNavController().navigate(R.id.action_moleculesFragment_to_h4TitleBodyViewFragment)
                                }
                                MOLECULE_H5_TITLE_BODY_VIEW -> {
                                    findNavController().navigate(R.id.action_moleculesFragment_to_h5TitleBodyViewFragment)
                                }
                            }
                        })
                    }
                }
            }
        }
        viewModel.getMoleculesData()
    }
}