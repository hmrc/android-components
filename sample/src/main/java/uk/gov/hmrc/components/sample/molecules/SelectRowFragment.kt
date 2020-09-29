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
package uk.gov.hmrc.components.sample.molecules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uk.gov.hmrc.components.molecule.selectrow.SelectRowGroup
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentSelectRowBinding

class SelectRowFragment : BaseComponentsFragment() {

    private var binding: FragmentSelectRowBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_select_row, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelectRowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            placeholderGroup.setError("Error text")

            selectRowGroup.setOnRowSelectedListener(object : SelectRowGroup.OnRowSelectedListener {
                override fun onRowSelected(id: Int) {
                    when (id) {
                        R.id.select_row_1 -> "First row selected"
                        R.id.select_row_2 -> "Second row selected"
                        R.id.select_row_3 -> {
                            selectRowGroup.setError("This is an error")
                            null
                        }
                        R.id.select_row_4 -> "Fourth row selected"
                        else -> null
                    }?.let {
                        showToast(it)
                    }
                }
            })
            selectRowTickGroup.selectedRow = R.id.select_row_tick_1
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
