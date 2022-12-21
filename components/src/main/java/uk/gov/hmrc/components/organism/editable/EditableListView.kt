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

open class EditableListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: ComponentEditableListViewBinding =
        ComponentEditableListViewBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var editableListViewAdapter: EditableListViewAdapter
    private var editableItem = ArrayList<EditableItem>()
    private var itemPosition: Int? = null
    private var editMode = true
    private var editButtonText: String =
        context.getString(R.string.editable_list_placeholder_button_title)

    init {
        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(it, R.styleable.EditableListView, 0, 0)
            val title = typedArray.getString(R.styleable.EditableListView_title) ?: ""
            editButtonText = typedArray.getString(R.styleable.EditableListView_editButtonText) ?: ""

            setTitle(title)
            setEditButtontext(editButtonText)
            typedArray.recycle()
        }

        binding.iconButton.setOnClickListener {
            if (::editableListViewAdapter.isInitialized) {
                editableListViewAdapter.isEditEnable = !editableListViewAdapter.isEditEnable
            }
            setEditModeUI(!editMode)
        }
    }

    private fun setEditModeUI(isInEditMode: Boolean) {
        this.editMode = isInEditMode
        binding.iconButton.apply {
            setIconResource(if (isInEditMode) R.drawable.ic_edit else R.drawable.ic_tick)
            text =
                if (isInEditMode) context.getString(R.string.edit_button_update_or_remove) else context.getString(
                    R.string.edit_button_finish
                )
        }
    }

    fun setData(editableItem: ArrayList<EditableItem>) {
        this.editableItem = editableItem

        editableListViewAdapter =
            EditableListViewAdapter(editableItem, editButtonText) { position ->
                itemPosition = position
            }

        binding.listItem.apply {
            adapter = editableListViewAdapter
        }
    }

    fun setEditButtontext(title: String) {
        editButtonText = title
        if (::editableListViewAdapter.isInitialized) {
            editableListViewAdapter.buttonText = title
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    interface EditableItem {
        var name: String
        var value: String
    }
}
