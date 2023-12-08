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
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override suspend fun getColorList() = ColorItem.values().asList()

    override suspend fun getAtomList() = arrayListOf(
        ComponentMenuItem(ATOM_TEXT, R.string.atoms_text),
        ComponentMenuItem(ATOM_BUTTON, R.string.atoms_buttons),
        ComponentMenuItem(ATOM_DIVIDER, R.string.atoms_divider)
    )

    override suspend fun getMoleculesList() = arrayListOf(
        ComponentMenuItem(MOLECULE_TEXT_INPUT_VIEW, R.string.molecules_text_input_view),
        ComponentMenuItem(MOLECULE_CURRENCY_INPUT_VIEW, R.string.molecules_currency_input_view),
        ComponentMenuItem(MOLECULE_H4_TITLE_BODY_VIEW, R.string.molecules_h4),
        ComponentMenuItem(MOLECULE_H5_TITLE_BODY_VIEW, R.string.molecules_h5),
        ComponentMenuItem(MOLECULE_BOLD_TITLE_BODY_VIEW, R.string.molecules_bold),
        ComponentMenuItem(MOLECULE_INSET_VIEW, R.string.molecules_inset),
        ComponentMenuItem(MOLECULE_INSET_TEXT_VIEW, R.string.molecules_inset_text),
        ComponentMenuItem(MOLECULE_MULTI_COLUMN_ROW_VIEW, R.string.molecules_multi_column_row),
        ComponentMenuItem(MOLECULE_SWITCH_ROW_VIEW, R.string.molecules_switch_row),
        ComponentMenuItem(MOLECULE_STATUS_VIEW, R.string.molecules_status),
        ComponentMenuItem(MOLECULE_WARNING_VIEW, R.string.molecules_warning),
        ComponentMenuItem(MOLECULE_TAB_BAR_VIEW, R.string.molecules_tab_bar),
        ComponentMenuItem(SELECT_ROW_VIEW, R.string.molecules_select_row),
    )

    override suspend fun getOrganismsList() = arrayListOf(
        ComponentMenuItem(ORGANISM_HEADLINE_CARD_VIEW, R.string.organisms_headline_card),
        ComponentMenuItem(ORGANISM_ICON_BUTTON_CARD_VIEW, R.string.organisms_icon_button_card),
        ComponentMenuItem(ORGANISM_SEPARATED_VIEW_CONTAINER, R.string.organisms_separated_view_container)
    )

    companion object {
        // ATOM
        const val ATOM_TEXT = 1
        const val ATOM_BUTTON = 2
        const val ATOM_DIVIDER = 3

        // MOLECULE
        const val MOLECULE_TEXT_INPUT_VIEW = 1
        const val MOLECULE_CURRENCY_INPUT_VIEW = 2
        const val MOLECULE_H4_TITLE_BODY_VIEW = 3
        const val MOLECULE_H5_TITLE_BODY_VIEW = 4
        const val MOLECULE_BOLD_TITLE_BODY_VIEW = 5
        const val MOLECULE_INSET_VIEW = 6
        const val MOLECULE_INSET_TEXT_VIEW = 7
        const val MOLECULE_MULTI_COLUMN_ROW_VIEW = 8
        const val MOLECULE_SWITCH_ROW_VIEW = 9
        const val MOLECULE_STATUS_VIEW = 10
        const val MOLECULE_WARNING_VIEW = 11
        const val MOLECULE_TAB_BAR_VIEW = 12
        const val SELECT_ROW_VIEW = 13

        // ORGANISM
        const val ORGANISM_HEADLINE_CARD_VIEW = 1
        const val ORGANISM_ICON_BUTTON_CARD_VIEW = 4
        const val ORGANISM_SEPARATED_VIEW_CONTAINER = 6
    }
}
