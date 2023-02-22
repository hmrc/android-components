/*
 * Copyright 2020 HM Revenue & Customs
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
package uk.gov.hmrc.components.sample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.gov.hmrc.components.organism.editable.EditableListItemViewState
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.databinding.FragmentComponentListBinding

abstract class BaseListFragment<ItemType, ViewHolder : RecyclerView.ViewHolder> : BaseComponentsFragment() {

    private var binding: FragmentComponentListBinding by autoCleared()

    private val adapter by lazy {
        provideAdapter()
    }

    abstract fun provideAdapter(): RecyclerView.Adapter<ViewHolder>

    abstract fun provideItems(): List<ItemType>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentComponentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.testElvJemma.apply {
            val editableItem = arrayListOf(
                EditableListItemViewState(
                    R.string.editable_list_view_placeholder_name,
                    "Column 2",
                    R.string.editable_list_view_placeholder_link_button
                ) { Toast.makeText(requireContext(), "test", LENGTH_LONG).show() },
                EditableListItemViewState(
                    R.string.editable_list_view_placeholder_name,
                    "Column 2",
                    R.string.editable_list_view_placeholder_link_button
                ) { }
            )
            setData(editableItem)
            setTitle(getString(R.string.editable_list_view_placeholder_title))
            setButtonData("Not in edit mode", "In edit mode")
            setButtonIconData(R.drawable.ic_help_outline, R.drawable.ic_help_outline)
        }

        binding.componentList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = this@BaseListFragment.adapter
        }
    }
}
