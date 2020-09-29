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
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentSwitchRowBinding

class SwitchRowFragment : BaseComponentsFragment() {

    private var binding: FragmentSwitchRowBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_switch_row, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSwitchRowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            switchRowPlaceholder.setCheckedChangedListener {
                switchRowPlaceholder.setBody(getString(if (it) R.string.switch_row_placeholder_body_on else R.string.switch_row_placeholder_body_off))
                showToast("Placeholder state: $it")
            }
            switchRowExample1.setCheckedChangedListener { showToast("Start of the month: $it") }
            switchRowExample2.setCheckedChangedListener { showToast("End of the month: $it") }
            switchRowExample3.setCheckedChangedListener { showToast("Fingerprint ID: $it") }
            switchRowExample4.setCheckedChangedListener { showToast("Lorem ipsum example 1: $it") }
            switchRowExample5.setCheckedChangedListener { showToast("Lorem ipsum example 2: $it") }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
