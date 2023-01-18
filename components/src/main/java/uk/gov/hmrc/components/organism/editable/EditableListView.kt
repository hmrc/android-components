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
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentEditableListViewBinding
import kotlin.random.Random

open class EditableListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private val binding: ComponentEditableListViewBinding =
        ComponentEditableListViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var buttonText: Pair<String, String> = Pair("", "")
    private var buttonAccessibility: Pair<String, String> = Pair("", "")
    private var buttonIcon: Pair<Int, Int> = Pair(0, 0)
    private lateinit var editableListViewAdapter: EditableListViewAdapter
    private var editableItems = ArrayList<EditableItem>()
    private var editMode = false

    init {
        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(it, R.styleable.EditableListView, 0, 0)
            val title = typedArray.getString(R.styleable.EditableListView_title) ?: ""
            setButtonData(
                typedArray.getString(R.styleable.EditableListView_buttonStartEditingText) ?: "",
                typedArray.getString(R.styleable.EditableListView_buttonFinishEditingText) ?: ""
            )
            setButtonIconData(
                typedArray.getResourceId(R.styleable.EditableListView_buttonStartEditingIcon, NO_ICON),
                typedArray.getResourceId(R.styleable.EditableListView_buttonFinishEditingIcon, NO_ICON)
            )
            setButtonAccessibility(
                typedArray.getString(R.styleable.EditableListView_startEditingAccessibility) ?: "",
                typedArray.getString(R.styleable.EditableListView_endEditingAccessibility) ?: ""
            )
            setTitle(title)
            typedArray.recycle()
        }
        binding.title.id = Random.nextInt()
        binding.iconButton.setOnClickListener {
            if (::editableListViewAdapter.isInitialized) {
                editableListViewAdapter.isEditEnable = !editableListViewAdapter.isEditEnable
            }
            setEditModeUI(!editMode)
        }
        setFocusListener()
    }

    private fun setEditModeUI(isInEditMode: Boolean) {
        this.editMode = isInEditMode
        binding.iconButton.apply {
            accessibilityTraversalBefore =
                if (isInEditMode) binding.title.id else nextFocusForwardId
            setIconResource(if (isInEditMode) buttonIcon.second else buttonIcon.first)
            announceForAccessibility(if (isInEditMode) buttonAccessibility.second else buttonAccessibility.first)
            text = if (isInEditMode) buttonText.second else buttonText.first
        }
    }

    fun setButtonData(startEditingText: String, endEditingText: String) {
        buttonText = Pair(startEditingText, endEditingText)
        binding.iconButton.text = if (editMode) endEditingText else startEditingText
    }

    fun setButtonIconData(
        @DrawableRes startEditingIcon: Int,
        @DrawableRes endEditingIcon: Int
    ) {
        buttonIcon = Pair(startEditingIcon, endEditingIcon)
        binding.iconButton.setIconResource(if (editMode) buttonIcon.second else buttonIcon.first)
    }

    fun setButtonAccessibility(
        startEditingAccessibility: String,
        endEditingAccessibility: String
    ) {
        buttonAccessibility = Pair(startEditingAccessibility, endEditingAccessibility)
    }

    fun setData(editableItem: ArrayList<EditableItem>) {
        this.editableItems = editableItem
        editableListViewAdapter = EditableListViewAdapter(editableItem)
        binding.listItems.apply {
            adapter = editableListViewAdapter
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    interface EditableItem {
        var name: String
        var value: String
        var buttonText: String
        var valueContentDescription: String
        val onClickListener: (Int) -> Unit
    }

    private fun setFocusListener() {
        ViewCompat.setAccessibilityDelegate(
            binding.root,
            object : AccessibilityDelegateCompat() {
                override fun onRequestSendAccessibilityEvent(
                    viewGroup: ViewGroup?,
                    child: View?,
                    event: AccessibilityEvent
                ): Boolean {
                    if (event.eventType == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
                        if (child?.id == binding.title.id) {
                            binding.iconButton.accessibilityTraversalBefore = nextFocusForwardId
                        }
                    }
                    return super.onRequestSendAccessibilityEvent(viewGroup, child, event)
                }
            }
        )
    }

    companion object {
        const val NO_ICON: Int = 0
    }
}
