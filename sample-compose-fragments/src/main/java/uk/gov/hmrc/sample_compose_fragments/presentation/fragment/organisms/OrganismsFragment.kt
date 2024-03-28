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
package uk.gov.hmrc.sample_compose_fragments.presentation.fragment.organisms

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
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ORGANISM_HEADLINE_CARD_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ORGANISM_ICON_BUTTON_CARD_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ORGANISM_INFORMATION_MESSAGE_CARD_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ORGANISM_PRIMARY_CARD_VIEW
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl.Companion.ORGANISM_SEPARATED_VIEW_CONTAINER
import uk.gov.hmrc.sample_compose_fragments.navigator.Navigator
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ComponentListScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.OrganismsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class OrganismsFragment : Fragment(R.layout.fragment_compose_example) {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: FragmentComposeExampleBinding
    private val viewModel: OrganismsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComposeExampleBinding.bind(view)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val listItems by viewModel.organismsItems.collectAsState()
                HmrcTheme {
                    HmrcSurface {
                        ComponentListScreen(items = listItems, navigateTo = {
                            when (it.id) {
                                ORGANISM_ICON_BUTTON_CARD_VIEW -> {
                                    with(navigator) { goToIconButtonCardView() }
                                }

                                ORGANISM_SEPARATED_VIEW_CONTAINER -> {
                                    with(navigator) { goToSeparatedViewContainer() }
                                }

                                ORGANISM_HEADLINE_CARD_VIEW -> {
                                    with(navigator) { goToHeadlineCardView() }
                                }

                                ORGANISM_PRIMARY_CARD_VIEW -> {
                                    with(navigator) { goToPrimaryCardView() }
                                }

                                ORGANISM_INFORMATION_MESSAGE_CARD_VIEW -> {
                                    with(navigator) { goToInformationMessageCardView() }
                                }
                            }
                        })
                    }
                }
            }
        }
        viewModel.getOrganismsData()
    }
}
