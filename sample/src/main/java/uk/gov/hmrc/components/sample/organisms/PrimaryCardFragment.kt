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
package uk.gov.hmrc.components.sample.organisms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentPrimaryCardBinding

class PrimaryCardFragment : BaseComponentsFragment() {

    private var binding: FragmentPrimaryCardBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.organisms_primary_card, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPrimaryCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            primaryExample3Button.setOnClickListener { onCtaPressed() }
            primaryExample4.removeChildPadding()
            primaryExample4Button.setOnClickListener { onCtaPressed() }
            primaryExample5Button.setOnClickListener { onCtaPressed() }
        }
    }

    private fun onCtaPressed() {
        Toast.makeText(activity, getString(R.string.cta_pressed), Toast.LENGTH_SHORT).show()
    }
}
