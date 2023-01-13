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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.RecyclerView
import uk.gov.hmrc.components.databinding.EditableListItemsBinding

class EditableListViewAdapter(
    private val values: List<EditableListView.EditableItem>,
) : RecyclerView.Adapter<EditableListViewAdapter.ViewHolder>() {

    var isEditEnable: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            EditableListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position], position + 1)
    }

    inner class ViewHolder(private val binding: EditableListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: EditableListView.EditableItem, position: Int) = with(binding) {
            columnOne.text = result.name
            columnTwo.text = result.value
            iconButton.text = result.buttonText
            itemView.contentDescription =
                if (isEditEnable) "${result.name} ${result.valueContentDescription} ${result.buttonText}" +
                    "${result.buttonContentDescription} Item $position of $itemCount" else {
                    "${result.name} ${result.valueContentDescription} Item $position of $itemCount"
                }
            iconButton.setOnClickListener {
                result.onClickListener(adapterPosition)
            }

            if (isEditEnable) {
                motionLayout.transitionToEnd()
            } else {
                motionLayout.transitionToStart()
            }

//            itemView.accessibilityDelegate = object : View.AccessibilityDelegate() {
//                override fun performAccessibilityAction(host: View?, action: Int, args: Bundle?): Boolean {
//                    if (action == AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS) {
//                        // Sends an accessibility event of accessibility focus type.
//                        host?.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED)
//                    }
//                    return super.performAccessibilityAction(host, action, args)
//                }
//            }

            itemView.isClickable = isEditEnable
            itemView.isFocusable = isEditEnable
            ViewCompat.setAccessibilityDelegate(
                itemView, object : AccessibilityDelegateCompat() {
                    override fun onInitializeAccessibilityNodeInfo(
                        host: View,
                        info: AccessibilityNodeInfoCompat
                    ) {
                        super.onInitializeAccessibilityNodeInfo(host, info)
                        info.roleDescription = if (isEditEnable) "tap to activate" else ""
                    }
                }
            )
        }
    }
}
