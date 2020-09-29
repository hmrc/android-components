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
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentTabBarBinding

class TabBarFragment : BaseComponentsFragment() {

    private var binding: FragmentTabBarBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_tab_bar, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTabBarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tabExample1.apply {
                addTab(newTab().setText("Light"))
                addTab(newTab().setText("Style"))
                addTab(newTab().setText("Tabs"))
            }
            tabExample2.apply {
                addTab(newTab().setText("Dark"))
                addTab(newTab().setText("Style"))
                addTab(newTab().setText("Tabs"))
            }
            tabExample3.apply {
                addTab(newTab().setText("When fixed width breaks"))
                addTab(newTab().setText("options align to left"))
                addTab(newTab().setText("and component becomes"))
                addTab(newTab().setText("scrollable"))
            }
        }
    }
}
