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
package uk.gov.hmrc.components.sample.atoms

import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.base.ComponentListFragment
import uk.gov.hmrc.components.sample.base.ComponentListItem
import uk.gov.hmrc.components.sample.base.ToolbarState

class AtomsFragment : ComponentListFragment() {

    override fun provideToolbar() = ToolbarState(true, R.string.title_atoms, false)

    override fun provideItems(): List<ComponentListItem> {
        return listOf(
            ComponentListItem(getString(R.string.atoms_text)) { selectFragment(TextFragment()) },
            ComponentListItem(getString(R.string.atoms_buttons)) { selectFragment(ButtonsFragment()) },
            ComponentListItem(getString(R.string.atoms_divider)) { selectFragment(DividerFragment()) }
        )
    }
}
