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
package uk.gov.hmrc.components.sample.colors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.base.BaseListFragment
import uk.gov.hmrc.components.sample.databinding.ItemColorBinding

abstract class ColorListFragment : BaseListFragment<ColorListItem, ColorAdapter.ViewHolder>() {

    override fun provideAdapter() = ColorAdapter(provideItems())
}

data class ColorListItem(val title: String, @ColorRes val color: Int)

class ColorAdapter(val items: List<ColorListItem>) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            colorSwatch.setBackgroundColor(ContextCompat.getColor(root.context, item.color))

            val colorNameWithHex = root.context.getString(
                R.string.color_name_with_hex,
                item.title,
                item.color.toString().uppercase()
            )
            colorName.text = colorNameWithHex
        }
    }

    class ViewHolder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root)
}
