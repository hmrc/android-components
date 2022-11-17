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
package uk.gov.hmrc.components.sample.molecules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentDonutChartViewBinding

class DonutChartViewFragment : BaseComponentsFragment() {
    private var binding: FragmentDonutChartViewBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_donut_chart_view, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDonutChartViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Suppress("MagicNumber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.donutPlaceholder.startAnimation(34f, 66f, false)
        binding.donutExample1.startAnimation(20f, 80f, true)
    }
}
