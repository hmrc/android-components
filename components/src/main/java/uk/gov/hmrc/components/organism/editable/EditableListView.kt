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
package uk.gov.hmrc.components.organism.editable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentEditableListViewBinding
import uk.gov.hmrc.components.extensions.setAccessibilityMessage

open class EditableListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: ComponentEditableListViewBinding =
        ComponentEditableListViewBinding.inflate(LayoutInflater.from(context), this, true)

    data class EditableItem(var name: String, var value: String)

    lateinit var editableListViewAdapter: EditableListViewAdapter
    var editableItem = ArrayList<EditableItem>()
    var itemPosition: Int? = null
    var clicked = false

    init {

        binding.secondaryButton.apply {

            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit, 0, 0, 0)
            text = context.getString(R.string.edit_button_update_or_remove)

            setOnClickListener {
                editableListViewAdapter.isEditEnable = !editableListViewAdapter.isEditEnable
                if (clicked) {
                    clicked = false
                    announceForAccessibility("Edit buttons now hidden")

                    setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit, 0, 0, 0)
                    text = context.getString(R.string.edit_button_update_or_remove)
                    setAccessibilityMessage("edit your company benefits")
                } else {
                    clicked = true
                    announceForAccessibility("Edit buttons now visible")

                    setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tick, 0, 0, 0)
                    text = context.getString(R.string.edit_button_finish)
                    setAccessibilityMessage("finish editing your company benefits")
                }
            }
        }
    }

    fun setData(editableItem: ArrayList<EditableItem>) {
        this.editableItem = editableItem

        editableListViewAdapter =
            EditableListViewAdapter(editableItem) { position ->
                itemPosition = position
            }

        binding.listItem.apply {
            adapter = editableListViewAdapter
        }
    }
}
