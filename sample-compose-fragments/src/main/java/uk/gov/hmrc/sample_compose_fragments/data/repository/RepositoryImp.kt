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
package uk.gov.hmrc.sample_compose_fragments.data.repository

import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.data.model.ComponentMenuItem
import uk.gov.hmrc.sample_compose_fragments.data.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.data.model.MoleculeItem
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override suspend fun getColorList() = ColorItem.values().asList()
    override suspend fun getAtomList() = arrayListOf(
        ComponentMenuItem(ATOM_TEXT, R.string.atoms_text),
        ComponentMenuItem(ATOM_BUTTON, R.string.atoms_buttons),
        ComponentMenuItem(ATOM_DIVIDER, R.string.atoms_divider)
    )
<<<<<<< HEAD

    override suspend fun getMoleculeList() = arrayListOf(
        MoleculeItem(1, R.string.molecules_h4)
    )
=======
    override suspend fun getMoleculesList() = arrayListOf(
        ComponentMenuItem(MOLECULE_INSET_VIEW, R.string.molecules_inset),
        ComponentMenuItem(MOLECULE_INSET_TEXT_VIEW, R.string.molecules_inset_text)
    )

    companion object {
        const val ATOM_TEXT = 1
        const val ATOM_BUTTON = 2
        const val ATOM_DIVIDER = 3

        const val MOLECULE_INSET_VIEW = 1
        const val MOLECULE_INSET_TEXT_VIEW = 2
    }
>>>>>>> main
}