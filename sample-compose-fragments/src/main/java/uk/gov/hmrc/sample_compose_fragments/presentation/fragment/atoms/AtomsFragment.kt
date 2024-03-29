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
package uk.gov.hmrc.sample_compose_fragments.presentation.fragment.atoms

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentComposeExampleBinding
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ATOM_BUTTON
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ATOM_DIVIDER
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ATOM_TEXT
import uk.gov.hmrc.sample_compose_fragments.navigator.Navigator
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ComponentListScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.AtomsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AtomsFragment : Fragment(R.layout.fragment_compose_example) {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: FragmentComposeExampleBinding
    private val viewModel: AtomsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComposeExampleBinding.bind(view)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val listItems by viewModel.atomsItems.collectAsState()
                HmrcTheme {
                    HmrcSurface {
                        ComponentListScreen(items = listItems, navigateTo = {
                            when (it.id) {
                                ATOM_TEXT -> with(navigator) { gotoAtomText() }
                                ATOM_BUTTON -> with(navigator) { gotoAtomButton() }
                                ATOM_DIVIDER -> with(navigator) { gotoAtomDivider() }
                            }
                        })
                    }
                }
            }
        }
        viewModel.getAtomsData()
    }
}