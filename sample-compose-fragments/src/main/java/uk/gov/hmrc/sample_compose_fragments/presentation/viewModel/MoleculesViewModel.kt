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
package uk.gov.hmrc.sample_compose_fragments.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.gov.hmrc.sample_compose_fragments.data.model.ComponentMenuItem
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MoleculesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _moleculesItems = MutableStateFlow(listOf<ComponentMenuItem>())
    val moleculesItems: StateFlow<List<ComponentMenuItem>> get() = _moleculesItems

    fun getMoleculesData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _moleculesItems.value = repository.getMoleculesList()
            }
        }
    }
}