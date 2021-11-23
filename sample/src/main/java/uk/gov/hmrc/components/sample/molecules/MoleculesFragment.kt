/*
 * Copyright 2020 HM Revenue & Customs
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
package uk.gov.hmrc.components.sample.molecules

import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.base.ComponentListFragment
import uk.gov.hmrc.components.sample.base.ComponentListItem
import uk.gov.hmrc.components.sample.base.ToolbarState

class MoleculesFragment : ComponentListFragment() {

    override fun provideToolbar() = ToolbarState(true, R.string.title_molecules, false)

    override fun provideItems(): List<ComponentListItem> {
        return listOf(
            ComponentListItem(getString(R.string.organisms_text_input)) { selectFragment(TextInputFragment()) },
            ComponentListItem(getString(R.string.organisms_currency_input)) { selectFragment(CurrencyInputFragment()) },
            ComponentListItem(getString(R.string.molecules_h4)) { selectFragment(H4TitleBodyViewFragment()) },
            ComponentListItem(getString(R.string.molecules_h5)) { selectFragment(H5TitleBodyViewFragment()) },
            ComponentListItem(getString(R.string.molecules_bold)) { selectFragment(BoldTitleBodyViewFragment()) },
            ComponentListItem(getString(R.string.molecules_inset)) { selectFragment(InsetViewFragment()) },
            ComponentListItem(getString(R.string.molecules_inset_text)) { selectFragment(InsetTextViewFragment()) },
            ComponentListItem(getString(R.string.molecules_multi_column_row)) { selectFragment(MultiColumnRowFragment()) },
            ComponentListItem(getString(R.string.molecules_switch_row)) { selectFragment(SwitchRowFragment()) },
            ComponentListItem(getString(R.string.molecules_status)) { selectFragment(StatusViewFragment()) },
            ComponentListItem(getString(R.string.molecules_warning)) { selectFragment(WarningViewFragment()) },
            ComponentListItem(getString(R.string.molecules_tab_bar)) { selectFragment(TabBarFragment()) },
            ComponentListItem(getString(R.string.molecules_select_row)) { selectFragment(SelectRowFragment()) }
        )
    }
}
