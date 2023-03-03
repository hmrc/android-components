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
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import uk.gov.hmrc.components.organism.editable.EditableListItemViewState
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

    @Suppress("LongMethod")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editableItem = arrayListOf(
            EditableListItemViewState(
                R.string.editable_list_view_placeholder_name,
                "Column 2",
                R.string.editable_list_view_placeholder_link_button
            ) { onCtaPressed() },
            EditableListItemViewState(
                R.string.editable_list_view_placeholder_name,
                "Column 2",
                R.string.editable_list_view_placeholder_link_button
            ) { onCtaPressed() }
        )
        val editableItem1 = arrayListOf(
            EditableListItemViewState(
                R.string.editable_list_view_example_1_name_r1,
                "£1000",
                R.string.editable_list_view_example_1_link_button,
                "1000 pounds",
                R.string.editable_list_view_web_browser_accessibility
            ) { onCtaPressed() },
            EditableListItemViewState(
                R.string.editable_list_view_example_1_name_r2,
                "£600",
                R.string.editable_list_view_example_1_link_button,
                "600 pounds",
                R.string.editable_list_view_web_browser_accessibility
            ) { onCtaPressed() },
            EditableListItemViewState(
                R.string.editable_list_view_example_1_name_r3,
                "£300000",
                R.string.editable_list_view_example_1_link_button,
                "300000 pounds",
                R.string.editable_list_view_web_browser_accessibility
            ) { onCtaPressed() },
            EditableListItemViewState(
                R.string.editable_list_view_example_1_name_r4,
                "£55500",
                R.string.editable_list_view_example_1_link_button,
                "55500 pounds"
            ) { onCtaPressed() },
        )
        val editableItem2 = arrayListOf(
            EditableListItemViewState(
                R.string.editable_list_view_example_2_name_r1,
                "78695743008",
                R.string.editable_list_view_example_2_link_button,
                "78695743008 pounds"
            ) { onCtaPressed() },
            EditableListItemViewState(
                R.string.editable_list_view_example_2_name_r1,
                "46970783733",
                R.string.editable_list_view_example_2_link_button,
                "46970783733 pounds"
            ) { onCtaPressed() }
        )
        binding.apply {
            editableListView.apply {
                setData(editableItem)
                setTitle(getString(R.string.editable_list_view_placeholder_title))
                setButtonData(
                    getString(R.string.editable_list_view_placeholder_start_button),
                    getString(R.string.editable_list_view_placeholder_end_button)
                )
                setButtonIconData(R.drawable.ic_help_outline, R.drawable.ic_help_outline)
                setButtonAccessibility(
                    getString(R.string.editable_list_view_placeholder_accessibility_start),
                    getString(R.string.editable_list_view_placeholder_accessibility_end)
                )
            }
            editableListExample1.apply {
                setData(editableItem1)
                setTitle(getString(R.string.editable_list_view_example_1_title))
                setButtonData(
                    getString(R.string.editable_list_view_example_1_start_button),
                    getString(R.string.editable_list_view_example_1_end_button)
                )
                setButtonIconData(R.drawable.ic_edit, R.drawable.ic_tick)
                setButtonAccessibility(
                    getString(R.string.editable_list_view_example_1_accessibility_start),
                    getString(R.string.editable_list_view_example_1_accessibility_end)
                )
                setIconButtonClickListener {
                    Toast.makeText(
                        requireContext(), "Additional custom click listener invoked", LENGTH_LONG
                    ).show()
                }
            }
            editableListExample2.apply {
                setData(editableItem2)
                setTitle(getString(R.string.editable_list_view_example_2_title))
                setButtonData(
                    getString(R.string.editable_list_view_example_2_start_button),
                    getString(R.string.editable_list_view_example_2_end_button)
                )
                setButtonIconData(R.drawable.ic_edit, R.drawable.ic_tick)
                setButtonAccessibility(
                    getString(R.string.editable_list_view_example_2_accessibility_start),
                    getString(R.string.editable_list_view_example_2_accessibility_end)
                )
            }
        }
    }

    private fun onCtaPressed() {
        Toast.makeText(activity, getString(R.string.cta_pressed), Toast.LENGTH_SHORT).show()
    }
}
