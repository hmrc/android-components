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

    val editableItem = arrayListOf<EditableListView.EditableItem>()

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
            EditableListViewModel(name = "Column 1", value = "Column 2"),
            EditableListViewModel(name = "Column 1", value = "Column 2")
        )

        val editableItem1 = arrayListOf<EditableListView.EditableItem>(
            EditableListViewModel(name = "Medical", value = "£1000"),
            EditableListViewModel(name = "Car Benefit", value = "£600"),
            EditableListViewModel(name = "Insurance", value = "£300000"),
            EditableListViewModel(name = "Tax Benefits", value = "£55500")
        )

        val editableItem2 = arrayListOf<EditableListView.EditableItem>(
            EditableListViewModel(name = "Lorem ipsum dolor", value = "78695743008"),
            EditableListViewModel(name = "Lorem ipsum dolor", value = "46970783733332")
        )

        binding.apply {
            editableListView.setData(editableItem)
            editableListExample1.setData(editableItem1)
            editableListExample2.setData(editableItem2)

            editableListView.setTitle("Title")
            editableListExample1.setTitle("Your Company Benefits")
            editableListExample2.setTitle("Lorem ipsum dolor sit amet, id cum ullum deseruisse solet.")

            editableListView.setEditButtontext("Column 3")
            editableListExample1.setEditButtontext("Edit")
            editableListExample2.setEditButtontext("Lorem ipsum")

            editableListView.setEditButtonContentDescription("dummy text")
            editableListExample1.setEditButtonContentDescription("Edit medical benefit")
            editableListExample2.setEditButtonContentDescription("Lorem ipsum dolor")

            editableListView.setEditbuttonData("(Link) Button Text", "(Link) Button Text")
            editableListExample1.setEditbuttonData(
                "Update or remove benefits",
                "Finish updating benefits"
            )
            editableListExample2.setEditbuttonData(
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor"
            )

            editableListView.setEditbuttonIconData(
                R.drawable.ic_help_outline,
                R.drawable.ic_help_outline
            )
            editableListExample1.setEditbuttonIconData(R.drawable.ic_edit, R.drawable.ic_tick)
            editableListExample2.setEditbuttonIconData(R.drawable.ic_edit, R.drawable.ic_tick)

            editableListView.setEditButtonAccessibility("Accessibility Text", "Accessibility Text")
            editableListExample1.setEditButtonAccessibility(
                "edit your company benefits",
                "Finish updating benefits"
            )
            editableListExample2.setEditButtonAccessibility(
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet"
            )
        }
    }

    data class EditableListViewModel(override var name: String, override var value: String) :
        EditableListView.EditableItem
}
