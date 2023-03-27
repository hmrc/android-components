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

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import uk.gov.hmrc.components.databinding.EditableListItemsBinding

class EditableListViewAdapter : RecyclerView.Adapter<EditableListViewAdapter.ViewHolder>() {

    var values: List<EditableListItemViewState> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    var firstPass: Boolean = true

    var isEditEnabled: Boolean = false
        @Suppress("NotifyDataSetChanged")
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
        holder.bind(values[position], position)
    }

    inner class ViewHolder(private val binding: EditableListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: EditableListItemViewState, position: Int) = with(binding) {
            val nameText = binding.root.context.getString(result.name)
            val actionButtonText = binding.root.context.getString(result.buttonText)

            columnOne.text = nameText
            columnTwo.text = result.value
            actionButton.text = actionButtonText

            val positionLabel = position + 1

            val additionalAccessibility = additionalActionAccessibility(result) ?: ""

            // temporary accessibility fix as first pass through ELV doesn't add row information
            val itemNumText = ", Item $positionLabel of $itemCount"

            divider.isVisible = positionLabel < itemCount
            itemView.contentDescription =
                if (isEditEnabled) {
                    "$nameText: ${valueAccessibility(result)}, " +
                        "$actionButtonText button, " + additionalAccessibility
                } else {
                    "$nameText: ${valueAccessibility(result)}" + if (firstPass) itemNumText else ""
                }

            if (isEditEnabled) {
                motionLayout.transitionToEnd()
                actionButton.isVisible = true
                itemView.setOnClickListener { result.onClickListener(adapterPosition) }
                actionButton.setOnClickListener { result.onClickListener(adapterPosition) }
                firstPass = false
            } else {
                itemView.isClickable = false
                actionButton.isClickable = false
                actionButton.isVisible = false
                motionLayout.transitionToStart()
            }
        }

        private fun additionalActionAccessibility(editableItem: EditableListItemViewState): String? =
            editableItem.additionalActionContentDescription?.let { binding.root.context.getString(it) }

        private fun valueAccessibility(editableItem: EditableListItemViewState): String =
            editableItem.valueContentDescription ?: editableItem.value
    }
}
