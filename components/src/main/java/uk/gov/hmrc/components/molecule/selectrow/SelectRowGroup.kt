/*
 * Copyright 2019 HM Revenue & Customs
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
package uk.gov.hmrc.components.molecule.selectrow

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentSelectRowGroupBinding

class SelectRowGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val binding: ComponentSelectRowGroupBinding =
        ComponentSelectRowGroupBinding.inflate(LayoutInflater.from(context), this)

    private val rows = mutableListOf<SelectRowView>()
    private var rowSelectedListener: OnRowSelectedListener? = null

    private var initialSelectedRow: Int = -1
    private var showDivider: Boolean = false

    var selectedRow = -1
        set(value) {
            if (value != field) {
                if (rows.isEmpty()) {
                    initialSelectedRow = value
                } else {
                    field = value
                    rows.forEach { row ->
                        row.findViewById<RadioButton>(R.id.select_row_radio_button).apply {
                            isChecked = row.id == value
                        }
                    }
                    rowSelectedListener?.onRowSelected(value)
                }
            }
        }

    private val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            initRows()
        }
    }

    init {
        orientation = VERTICAL
        layoutTransition = LayoutTransition()

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.SelectRowGroup, 0, 0)
            initialSelectedRow = typedArray.getResourceId(R.styleable.SelectRowGroup_selectedRow, -1)
            showDivider = typedArray.getBoolean(R.styleable.SelectRowGroup_showSelectRowDivider, false)
            typedArray.recycle()
        }

        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        // Moves any select row views inside select_row_options
        (0..childCount).map { getChildAt(it) }.filterIsInstance<SelectRowView>().forEachIndexed { index, selectRow ->
            removeView(selectRow)
            binding.selectRowOptions.addView(selectRow)
            if (showDivider && index < childCount) {
                binding.selectRowOptions.addView(createDivider())
            }
        }
    }

    fun setError(error: String) {
        binding.selectRowError.apply {
            text = error
            if (error.isNotEmpty()) {
                val errorContentDescription = context.getString(R.string.accessibility_error_prefix, error)
                contentDescription = errorContentDescription
                announceForAccessibility(errorContentDescription)
                visibility = View.VISIBLE
            } else {
                contentDescription = error
                visibility = View.GONE
            }
        }
    }

    fun setOnRowSelectedListener(listener: OnRowSelectedListener?) {
        rowSelectedListener = listener
    }

    override fun onSaveInstanceState(): Parcelable? {
        return Bundle().apply {
            putParcelable(STATE_SUPER, super.onSaveInstanceState())
            putInt(STATE_SELECTED_ROW, selectedRow)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? Bundle)?.let {
            selectedRow = it.getInt(STATE_SELECTED_ROW)
            super.onRestoreInstanceState(it.getParcelable(STATE_SUPER))
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        rowSelectedListener = null
    }

    private fun initRows() {
        (0..binding.selectRowOptions.childCount)
            .map { binding.selectRowOptions.getChildAt(it) }
            .filterIsInstance<SelectRowView>()
            .forEach { child ->
                child.findViewById<RadioButton>(R.id.select_row_radio_button).apply {
                    setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) selectedRow = child.id
                        child.updateContentDescription()
                    }
                    rows.add(child)
                    isChecked = child.id == initialSelectedRow
                }
            }
    }

    private fun createDivider() = View(context).apply {
        background = ContextCompat.getDrawable(context, R.drawable.components_divider)
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
            marginEnd = resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16)
        }
    }

    companion object {
        private const val STATE_SELECTED_ROW = "STATE_SELECTED_ROW"
        private const val STATE_SUPER = "STATE_SUPER"
    }

    interface OnRowSelectedListener {
        fun onRowSelected(id: Int)
    }
}
