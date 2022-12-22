/*
 * Copyright 2022 HM Revenue & Customs
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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.gov.hmrc.components.organism.editable.EditableListView
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentEditableListViewBinding

class EditableListViewFragment : BaseComponentsFragment() {

    private var binding: FragmentEditableListViewBinding by autoCleared()
    override fun provideToolbar() = ToolbarState(true, R.string.molecules_editable_list_view, true)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditableListViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editableItem = arrayListOf<EditableListView.EditableItem>(
            EditableListViewModel(
                "Column 1", "Column 2", "Column 3", "dummy text"
            ) {},
            EditableListViewModel(
                "Column 1", "Column 2", "Column 3", "dummy text"
            ) {}
        )
        val editableItem1 = arrayListOf<EditableListView.EditableItem>(
            EditableListViewModel(
                "Medical", "£1000",
                "Edit", "Edit Medical"
            ) {},
            EditableListViewModel(
                "Car Benefit", "£600", "Edit", "Edit Car Benefit"
            ) {},
            EditableListViewModel(
                "Insurance", "£300000", "Edit", "Edit Insurance"
            ) {},
            EditableListViewModel(
                "Tax Benefits", "£55500", "Edit",
                "Edit Tax Benefits"
            ) {}
        )
        val editableItem2 = arrayListOf<EditableListView.EditableItem>(
            EditableListViewModel(
                "Lorem ipsum dolor", "78695743008", "Lorem ipsum",
                "Lorem ipsum dolor"
            ) {},
            EditableListViewModel(
                "Lorem ipsum dolor", "46970783733", "Lorem ipsum", "Lorem ipsum dolor"
            ) {}
        )
        binding.apply {
            editableListView.apply {
                setData(editableItem)
                setTitle("Title")
                setEditbuttonData("(Link) Button Text", "(Link) Button Text")
                setEditbuttonIconData(R.drawable.ic_help_outline, R.drawable.ic_help_outline)
                setEditButtonAccessibility("Accessibility Text", "Accessibility Text")
            }
            editableListExample1.apply {
                setData(editableItem1)
                setTitle("Your Company Benefits")
                setEditbuttonData("Update or remove benefits", "Finish updating benefits")
                setEditbuttonIconData(R.drawable.ic_edit, R.drawable.ic_tick)
                setEditButtonAccessibility("edit your company benefits", "Finish updating benefits")
            }
            editableListExample2.apply {
                setData(editableItem2)
                setTitle("Lorem ipsum dolor sit amet, id cum ullum deseruisse solet.")
                setEditbuttonData("Lorem ipsum dolor sit amet", "Lorem ipsum dolor")
                setEditbuttonIconData(R.drawable.ic_edit, R.drawable.ic_tick)
                setEditButtonAccessibility("Lorem ipsum", "Lorem ipsum")
            }
        }
    }

    data class EditableListViewModel(
        override var name: String,
        override var value: String,
        override var buttonText: String,
        override var buttonContentDescription: String,
        override val onClickListener: (Int) -> Unit
    ) : EditableListView.EditableItem
}
