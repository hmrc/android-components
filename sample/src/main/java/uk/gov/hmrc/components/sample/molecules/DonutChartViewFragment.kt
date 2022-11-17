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
import uk.gov.hmrc.components.sample.databinding.FragmentDonutChartViewBinding
import uk.gov.hmrc.components.sample.databinding.FragmentMultiColumnRowBinding

class DonutChartViewFragment: BaseComponentsFragment() {
    private var binding: FragmentDonutChartViewBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_donut_chart_view, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDonutChartViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.donutPlaceholder.startAnimation(34f, 66f, false)
    }
}