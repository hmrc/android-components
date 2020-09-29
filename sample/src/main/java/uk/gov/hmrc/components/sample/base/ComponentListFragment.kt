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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.gov.hmrc.components.sample.databinding.ItemComponentBinding

abstract class ComponentListFragment : BaseListFragment<ComponentListItem, ComponentAdapter.ViewHolder>() {

    override fun provideAdapter() = ComponentAdapter(provideItems())
}

data class ComponentListItem(val title: String, val clickListener: () -> Unit = {})

class ComponentAdapter(val items: List<ComponentListItem>) : RecyclerView.Adapter<ComponentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemComponentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            componentName.text = item.title
            root.setOnClickListener { item.clickListener.invoke() }
        }
    }

    class ViewHolder(val binding: ItemComponentBinding) : RecyclerView.ViewHolder(binding.root)
}
