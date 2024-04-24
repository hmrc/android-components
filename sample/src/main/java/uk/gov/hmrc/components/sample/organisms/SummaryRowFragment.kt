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
import uk.gov.hmrc.components.molecule.item.MultiColumnRowView
import uk.gov.hmrc.components.organism.summary.SummaryRowView
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentSummaryRowBinding

class SummaryRowFragment : BaseComponentsFragment() {

    private var binding: FragmentSummaryRowBinding by autoCleared()

    override fun provideToolbar() = ToolbarState(true, R.string.organisms_summary_row, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSummaryRowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPlaceholderRow()
        setupExampleRows()
    }

    private fun setupPlaceholderRow() {
        val placeholderRow = MultiColumnRowView(requireContext())
        placeholderRow.setText(
            getString(R.string.summary_row_placeholder_row1_text1),
            getString(R.string.summary_row_placeholder_row1_text2),
            getString(R.string.summary_row_placeholder_row1_text3)
        )
        binding.summaryRowPlaceholder.apply {
            setRows(arrayListOf(placeholderRow))
            setOnClickListener { onCtaPressed() }
        }
    }

    @Suppress("LongMethod", "ComplexMethod")
    private fun setupExampleRows() {
        val example1aRow1 = MultiColumnRowView(requireContext())
        example1aRow1.apply {
            val text1 = getString(R.string.summary_row_example_1a_row1_text1)
            val text2 = getString(R.string.summary_row_example_1a_row1_text2)
            setText(text1, text2)
            setWholeRowContentDescription("$text1: $text2")
        }
        val example1aRow2 = MultiColumnRowView(requireContext())
        example1aRow2.apply {
            val text1 = getString(R.string.summary_row_example_1a_row2_text1)
            val text2 = getString(R.string.summary_row_example_1a_row2_text2)
            setText(text1, text2)
            setWholeRowContentDescription("$text1: $text2")
        }
        val example1aRow3 = MultiColumnRowView(requireContext())
        example1aRow3.apply {
            val text1 = getString(R.string.summary_row_example_1a_row3_text1)
            val text2 = getString(R.string.summary_row_example_1a_row3_text2)
            setText(text1, text2)
            setWholeRowContentDescription("$text1: $text2")
        }
        binding.summaryRowExample1a.apply {
            setRows(arrayListOf(example1aRow1, example1aRow2, example1aRow3))
            setOnClickListener { onCtaPressed() }
            setChevronContentDescription(getString(R.string.summary_row_example_1a_accessibility_message))
            setTitleTextAppearance(R.style.Text_Info)
        }

        val example1bRow1 = MultiColumnRowView(requireContext())
        example1bRow1.setText(
            getString(R.string.summary_row_example_1b_row1_text1),
            getString(R.string.summary_row_example_1b_row1_text2)
        )
        val example1bRow2 = MultiColumnRowView(requireContext())
        example1bRow2.setText(
            getString(R.string.summary_row_example_1b_row2_text1),
            getString(R.string.summary_row_example_1b_row2_text2)
        )
        binding.summaryRowExample1b.apply {
            setRows(arrayListOf(example1bRow1, example1bRow2))
            setOnClickListener { onCtaPressed() }
        }

        val example2Row1 = MultiColumnRowView(requireContext())
        example2Row1.setText(getString(R.string.summary_row_example_2_row1_text1))
        binding.summaryRowExample2.apply {
            setRows(arrayListOf(example2Row1))
            setOnClickListener { onCtaPressed() }
            setButtonAccessibilityMessage(
                getString(R.string.summary_row_example_2_accessibility_title),
                getString(R.string.summary_row_example_2_accessibility_action)
            )
        }

        val example3Row1 = MultiColumnRowView(requireContext())
        example3Row1.setText(getString(R.string.longest_text))
        binding.summaryRowExample3.apply {
            setRows(arrayListOf(example3Row1))
            setOnClickListener { onCtaPressed() }
        }

        val example4Row1 = MultiColumnRowView(requireContext())
        example4Row1.setText(getString(R.string.long_text), getString(R.string.long_text))
        binding.summaryRowExample4.setRows(arrayListOf(example4Row1))

        val example5Row1 = MultiColumnRowView(requireContext())
        example5Row1
            .setText(getString(R.string.long_text), getString(R.string.long_text), getString(R.string.long_text))
        val example5Row2 = MultiColumnRowView(requireContext())
        example5Row2.setText(getString(R.string.long_text))
        binding.summaryRowExample5.apply {
            setRows(arrayListOf(example5Row1, example5Row2))
            setOnClickListener { onCtaPressed() }
        }

        val example6Row1 = MultiColumnRowView(requireContext())
        example6Row1.setText(text1 = getString(R.string.long_text))
        binding.summaryRowExample6.apply {
            setRows(arrayListOf(example6Row1))
            setOnClickListener { onCtaPressed() }
            setIconTintColor(R.color.hmrc_blue)
        }

        // Example: SummaryRowView that is dynamically added
        binding.containerSummaryRowView.apply {
            removeAllViews()
            addView(createDynamicSummaryRowView())
        }
    }

    private fun createDynamicSummaryRowView(): SummaryRowView {
        val row = MultiColumnRowView(requireContext())
        row.setText(getString(R.string.long_text))
        return SummaryRowView(requireContext()).apply {
            readerTrait = SummaryRowView.READER_TRAIT_SIMPLE
            setTitle(getString(R.string.summary_row_example_6_title))
            setRows(arrayListOf(row))
            setOnClickListener {
                onCtaPressed()
            }
        }
    }

    private fun onCtaPressed() {
        Toast.makeText(activity, getString(R.string.cta_pressed), Toast.LENGTH_SHORT).show()
    }
}
