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
import androidx.annotation.DrawableRes
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

    private lateinit var editableListViewAdapter: EditableListViewAdapter
    private var editableItem = ArrayList<EditableItem>()
    private var itemPosition: Int? = null
    private var editMode = false
    private var editButtonText: String = ""
    private var editButtonContentDescription: String = ""
    private var buttonText: Pair<String, String> = Pair("", "")
    private var buttonAccessibility: Pair<String, String> = Pair("", "")
    private var buttonIcon: Pair<Int, Int> = Pair(0, 0)

    init {
        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(it, R.styleable.EditableListView, 0, 0)
            val title = typedArray.getString(R.styleable.EditableListView_title) ?: ""
            editButtonText = typedArray.getString(R.styleable.EditableListView_editbuttontext) ?: ""
            editButtonContentDescription =
                typedArray.getString(R.styleable.EditableListView_editbuttoncontentdescription)
                    ?: ""
            setEditbuttonData(
                typedArray.getString(R.styleable.EditableListView_buttonstarteditingtext) ?: "",
                typedArray.getString(R.styleable.EditableListView_buttonfinisheditingtext) ?: ""
            )
            setEditbuttonIconData(
                typedArray.getResourceId(
                    R.styleable.EditableListView_buttonstarteditingicon,
                    NO_ICON
                ),
                typedArray.getResourceId(
                    R.styleable.EditableListView_buttonfinisheditingicon,
                    NO_ICON
                )
            )
            setEditButtonAccessibility(
                typedArray.getString(R.styleable.EditableListView_statingeditingaccessibility)
                    ?: "",
                typedArray.getString(R.styleable.EditableListView_endeditingaccessibility) ?: ""
            )
            setTitle(title)
            setEditButtontext(editButtonText)
            setEditButtonContentDescription(editButtonContentDescription)
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
            setIconResource(if (isInEditMode) buttonIcon.first else buttonIcon.second)
            setAccessibilityMessage(if (isInEditMode) buttonAccessibility.first else buttonAccessibility.second)
            text = if (isInEditMode) buttonText.first else buttonText.second
        }
    }

    fun setEditbuttonData(statingEditingText: String, endEditingText: String) {
        buttonText = Pair(statingEditingText, endEditingText)
        binding.iconButton.apply {
            text = if (editMode) buttonText.first else buttonText.second
        }
    }

    fun setEditbuttonIconData(
        @DrawableRes statingEditingIcon: Int,
        @DrawableRes endEditingIcon: Int
    ) {
        buttonIcon = Pair(statingEditingIcon, endEditingIcon)
        binding.iconButton.apply {
            setIconResource(if (isInEditMode) buttonIcon.first else buttonIcon.second)
        }
    }

    fun setEditButtonAccessibility(
        statingEditingAccessibility: String,
        endEditingAccessibility: String
    ) {
        buttonAccessibility = Pair(statingEditingAccessibility, endEditingAccessibility)
        binding.iconButton.apply {
            setAccessibilityMessage(if (isInEditMode) buttonAccessibility.first else buttonAccessibility.second)
        }
    }

    fun setData(editableItem: ArrayList<EditableItem>) {
        this.editableItem = editableItem

        editableListViewAdapter =
            EditableListViewAdapter(
                editableItem,
                editButtonText,
                editButtonContentDescription
            ) { position ->
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

    fun setEditButtonContentDescription(description: String) {
        editButtonContentDescription = description
        if (::editableListViewAdapter.isInitialized) {
            editableListViewAdapter.buttonContentDescription = description
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    interface EditableItem {
        var name: String
        var value: String
    }

    companion object {
        const val NO_ICON: Int = 0
    }
}
