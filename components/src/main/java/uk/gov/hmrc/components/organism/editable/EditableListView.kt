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
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.size
import kotlinx.android.synthetic.main.component_editable_list_view.view.icon_button
import kotlinx.android.synthetic.main.component_editable_list_view.view.title
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentEditableListViewBinding


open class EditableListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: ComponentEditableListViewBinding =
        ComponentEditableListViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var buttonText: Pair<String, String> = Pair("", "")
    private var buttonAccessibility: Pair<String, String> = Pair("", "")
    private var buttonIcon: Pair<Int, Int> = Pair(0, 0)
    private lateinit var editableListViewAdapter: EditableListViewAdapter
    private var editableItems = ArrayList<EditableItem>()
    private var editMode = false
    private var millSec = "1800"

    init {
        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(it, R.styleable.EditableListView, 0, 0)
            val title = typedArray.getString(R.styleable.EditableListView_title) ?: ""
            setButtonData(
                typedArray.getString(R.styleable.EditableListView_buttonstarteditingtext) ?: "",
                typedArray.getString(R.styleable.EditableListView_buttonfinisheditingtext) ?: ""
            )
            setButtonIconData(
                typedArray.getResourceId(
                    R.styleable.EditableListView_buttonstarteditingicon,
                    NO_ICON
                ),
                typedArray.getResourceId(
                    R.styleable.EditableListView_buttonfinisheditingicon,
                    NO_ICON
                )
            )
            setButtonAccessibility(
                typedArray.getString(R.styleable.EditableListView_statingeditingaccessibility)
                    ?: "",
                typedArray.getString(R.styleable.EditableListView_endeditingaccessibility) ?: ""
            )
            setTitle(title)
            typedArray.recycle()
        }

        //binding.title.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
        // if (hasFocus) {

        //}
        //  }

        ViewCompat.setAccessibilityDelegate(
            binding.iconButton,
            object : AccessibilityDelegateCompat() {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View?,
                    info: AccessibilityNodeInfoCompat?
                ) {
                    info?.setTraversalAfter(binding.iconButton)
                    super.onInitializeAccessibilityNodeInfo(host, info)
                }
            }
        )

        binding.iconButton.setOnClickListener {
//            with(binding) {
//                //title.accessibilityTraversalAfter = binding.iconButton.id
//                title.setAccessibleFocusAfter(it)
//                listItems.setAccessibleFocusAfter(title)
//                listItems.setAccessibleFocusAfter(it)
//                importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
//
//            }
            if (!this.editMode) {
                binding.title.setAccessibleFocusAfter(it)
                binding.listItems.setAccessibleFocusAfter(title)
                binding.iconButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                binding.iconButton.accessibilityTraversalBefore = binding.iconButton.id
            } else {
                binding.iconButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                binding.iconButton.accessibilityTraversalBefore = binding.iconButton.id
            }
            if (::editableListViewAdapter.isInitialized) {
                editableListViewAdapter.isEditEnable = !editableListViewAdapter.isEditEnable
            }
            setEditModeUI(!editMode)
        }
    }

    private fun setEditModeUI(isInEditMode: Boolean) {
        this.editMode = isInEditMode
        binding.iconButton.apply {
            setIconResource(if (isInEditMode) buttonIcon.second else buttonIcon.first)
            announceForAccessibility(if (isInEditMode) buttonAccessibility.second else buttonAccessibility.first)
            text = if (editMode) buttonText.second else buttonText.first
        }
    }

    fun setButtonData(startEditingText: String, endEditingText: String) {
        buttonText = Pair(startEditingText, endEditingText)
        binding.iconButton.apply {
            text = if (editMode) buttonText.second else buttonText.first
        }
    }

    fun setButtonIconData(
        @DrawableRes startEditingIcon: Int,
        @DrawableRes endEditingIcon: Int
    ) {
        buttonIcon = Pair(startEditingIcon, endEditingIcon)
        binding.iconButton.apply {
            setIconResource(if (isInEditMode) buttonIcon.second else buttonIcon.first)
        }
    }

    fun setButtonAccessibility(
        startEditingAccessibility: String,
        endEditingAccessibility: String
    ) {
        buttonAccessibility = Pair(startEditingAccessibility, endEditingAccessibility)
        binding.iconButton.apply {
            announceForAccessibility(if (isInEditMode) buttonAccessibility.second else buttonAccessibility.first)
        }
    }

    fun setData(editableItem: ArrayList<EditableItem>) {
        this.editableItems = editableItem
        editableListViewAdapter = EditableListViewAdapter(editableItem)
        binding.listItems.apply {
            adapter = editableListViewAdapter
        }
    }

    fun setTitle(title: CharSequence?) {
        binding.title.text = title
    }

    interface EditableItem {
        var name: String
        var value: String
        var buttonText: String
        var buttonContentDescription: String
        var valueContentDescription: String
        val onClickListener: (Int) -> Unit
    }

    companion object {
        const val NO_ICON: Int = 0
    }
}

fun View.setAccessibleFocusAfter(otherView: View) {
    ViewCompat.setAccessibilityDelegate(
        this,
        object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View?,
                info: AccessibilityNodeInfoCompat?
            ) {
                //info?.setTraversalBefore(binding.listItems)
                info?.setTraversalAfter(otherView)
                super.onInitializeAccessibilityNodeInfo(host, info)
            }
        }
    )
}