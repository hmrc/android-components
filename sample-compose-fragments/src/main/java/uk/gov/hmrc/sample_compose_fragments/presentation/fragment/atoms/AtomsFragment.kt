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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.FragmentAtomsBinding
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.ComponentListScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.AtomsViewModel

@AndroidEntryPoint
class AtomsFragment : Fragment(R.layout.fragment_atoms) {

    private lateinit var binding: FragmentAtomsBinding
    private val viewModel: AtomsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAtomsBinding.bind(view)
        binding.composeViewAtoms.setContent {
            val listItems by viewModel.atomsItems.collectAsState()
            HmrcTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight().fillMaxWidth(),
                    color = HmrcTheme.colors.hmrcPageBackground
                ) {
                    ComponentListScreen(items = listItems, navigateTo = {
                        if (it.id == 1) {
                            findNavController().navigate(R.id.action_atomsFragment_to_textFragment)
                        }
                    })
                }
            }
        }
        viewModel.getAtomsData()
    }
}