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
package uk.gov.hmrc.components.sample.organisms

import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.base.ComponentListFragment
import uk.gov.hmrc.components.sample.base.ComponentListItem
import uk.gov.hmrc.components.sample.base.ToolbarState

class OrganismsFragment : ComponentListFragment() {

    override fun provideToolbar() = ToolbarState(true, R.string.title_organisms, false)

    override fun provideItems(): List<ComponentListItem> {
        return listOf(
            ComponentListItem(getString(R.string.organisms_headline_card)) { selectFragment(HeadlineCardFragment()) },
            ComponentListItem(getString(R.string.organisms_primary_card)) { selectFragment(PrimaryCardFragment()) },
            ComponentListItem(getString(R.string.organisms_status_card)) { selectFragment(StatusCardFragment()) },
            ComponentListItem(getString(R.string.organisms_icon_button_card)) { selectFragment(IconButtonCardFragment()) },
            ComponentListItem(getString(R.string.organisms_summary_row)) { selectFragment(SummaryRowFragment()) },
            ComponentListItem(getString(R.string.organisms_separated_view_container)) { selectFragment(SeparatedViewContainerFragment()) },
            ComponentListItem(getString(R.string.organisms_info_message_card)) { selectFragment(InformationMessageCardFragment()) },
            ComponentListItem(getString(R.string.organisms_menu_panel_row)) { selectFragment(MenuPanelRowFragment()) },
            ComponentListItem(getString(R.string.organisms_editable_list_view)) {
                selectFragment(EditableListViewFragment())
            },
            ComponentListItem(getString(R.string.organisms_mini_advert_card)) {
                selectFragment(MiniAdvertCardFragment())
            }
        )
    }
}
