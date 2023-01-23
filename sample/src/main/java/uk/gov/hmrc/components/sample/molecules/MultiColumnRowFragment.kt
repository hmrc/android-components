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
import android.widget.Toast.LENGTH_LONG
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentMultiColumnRowBinding

class MultiColumnRowFragment : BaseComponentsFragment() {

    private var binding: FragmentMultiColumnRowBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_multi_column_row, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMultiColumnRowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.multiColumnRowPlaceholder.apply {
            setText2ClickAction { Toast.makeText(context, "text 2 clicked", LENGTH_LONG).show() }
            setText3ClickAction("do custom action") {
                Toast.makeText(context, "text 3 custom action invoked", LENGTH_LONG).show()
            }
        }

        binding.multiColumnRowExample3.apply {
            setWholeRowContentDescription(getString(R.string.multi_column_row_example_text_1_2_content_description))
        }
    }
}
