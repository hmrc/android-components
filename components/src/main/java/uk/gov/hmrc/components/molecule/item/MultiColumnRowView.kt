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
package uk.gov.hmrc.components.molecule.item

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentMultiColumnRowBinding
import uk.gov.hmrc.components.extensions.setAsAccessibilityHeading
import uk.gov.hmrc.components.extensions.setStyle

class MultiColumnRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val binding: ComponentMultiColumnRowBinding =
            ComponentMultiColumnRowBinding.inflate(LayoutInflater.from(context), this)

    // show columns as rows if font size is scaled
    private val isMultiRow: Boolean
        get() = resources.configuration.fontScale > MAX_SINGLE_LINE_FONT_SCALE

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.MultiColumnRowView, 0, 0)

            val text1 = typedArray.getString(R.styleable.MultiColumnRowView_text1)
            val text2 = typedArray.getString(R.styleable.MultiColumnRowView_text2)
            val text3 = typedArray.getString(R.styleable.MultiColumnRowView_text3)
            val text1ContentDescription = typedArray.getString(R.styleable.MultiColumnRowView_text1ContentDescription)
            val text2ContentDescription = typedArray.getString(R.styleable.MultiColumnRowView_text2ContentDescription)
            val text3ContentDescription = typedArray.getString(R.styleable.MultiColumnRowView_text3ContentDescription)
            val text1IsSelectable = typedArray.getBoolean(R.styleable.MultiColumnRowView_text1IsSelectable, false)
            val text2IsSelectable = typedArray.getBoolean(R.styleable.MultiColumnRowView_text2IsSelectable, false)
            val text3IsSelectable = typedArray.getBoolean(R.styleable.MultiColumnRowView_text3IsSelectable, false)
            val textStyle = typedArray.getResourceId(R.styleable.MultiColumnRowView_textStyle, R.style.Text_Body)
            val textStyle2 = typedArray.getResourceId(R.styleable.MultiColumnRowView_textStyle2, textStyle)
            val textStyle3 = typedArray.getResourceId(R.styleable.MultiColumnRowView_textStyle3, textStyle)
            val text1Heading = typedArray.getBoolean(R.styleable.MultiColumnRowView_text1Heading, false)

            setText(text1, text2, text3)
            setTextContentDesription(text1ContentDescription, text2ContentDescription, text3ContentDescription)
            setTextIsSelectable(text1IsSelectable, text2IsSelectable, text3IsSelectable)
            setTextStyle(textStyle, textStyle2, textStyle3)
            setText1AsHeading(text1Heading)

            typedArray.recycle()
        }

        orientation = if (isMultiRow) {
            VERTICAL
        } else {
            HORIZONTAL
        }
    }

    fun setText(text1: CharSequence?, text2: CharSequence? = null, text3: CharSequence? = null) {
        binding.apply {
            rowText1.text = text1
            rowText2.text = text2
            rowText3.text = text3
        }

        val showText2 = !text2.isNullOrEmpty()
        val showText3 = !text3.isNullOrEmpty()

        binding.apply {
            rowText1.layoutParams = getText1LayoutParams(showText2, showText3)
            rowText2.layoutParams = getOptionalTextLayoutParams(showText2)
            rowText3.layoutParams = getOptionalTextLayoutParams(showText3)
        }

        val multiColumnTextGravity = if (isMultiRow) {
            Gravity.START
        } else {
            Gravity.END
        }

        binding.apply {
            rowText2.gravity = multiColumnTextGravity
            rowText3.gravity = multiColumnTextGravity
        }
    }

    private fun getText1LayoutParams(isText2Visible: Boolean, isText3Visible: Boolean): LayoutParams {
        val text1Weight = if (isText2Visible && !isText3Visible) 2.0f else 1.0f

        return LayoutParams(
                getTextLayoutWidth(),
                LayoutParams.WRAP_CONTENT,
                text1Weight
        )
    }

    private fun getOptionalTextLayoutParams(isVisible: Boolean): LayoutParams {
        val textWeight = if (isVisible) 1.0f else 0.0f
        val textLayoutHeight = if (isVisible) LayoutParams.WRAP_CONTENT else 0

        return LayoutParams(
                getTextLayoutWidth(),
                textLayoutHeight,
                textWeight
        )
    }

    private fun getTextLayoutWidth(): Int {
        return if (isMultiRow) {
            LayoutParams.MATCH_PARENT
        } else {
            0
        }
    }

    fun setText1(text1: CharSequence?) {
        setText(text1, binding.rowText2.text, binding.rowText3.text)
    }

    fun setText2(text2: CharSequence?) {
        setText(binding.rowText1.text, text2, binding.rowText3.text)
    }

    fun setText3(text3: CharSequence?) {
        setText(binding.rowText1.text, binding.rowText2.text, text3)
    }

    fun setTextContentDesription(desc1: CharSequence?, desc2: CharSequence?, desc3: CharSequence?) {
        desc1?.let { binding.rowText1.contentDescription = it }
        desc2?.let { binding.rowText2.contentDescription = it }
        desc3?.let { binding.rowText3.contentDescription = it }
    }

    fun setText1ContentDescription(desc1: CharSequence?) {
        setTextContentDesription(desc1, binding.rowText2.contentDescription, binding.rowText3.contentDescription)
    }

    fun setText2ContentDescription(desc2: CharSequence?) {
        setTextContentDesription(binding.rowText1.contentDescription, desc2, binding.rowText3.contentDescription)
    }

    fun setText3ContentDescription(desc3: CharSequence?) {
        setTextContentDesription(binding.rowText1.contentDescription, binding.rowText2.contentDescription, desc3)
    }

    fun setTextIsSelectable(
        text1IsSelectable: Boolean = binding.rowText1.isTextSelectable,
        text2IsSelectable: Boolean = binding.rowText2.isTextSelectable,
        text3IsSelectable: Boolean = binding.rowText3.isTextSelectable
    ) {
        binding.apply {
            rowText1.setTextIsSelectable(text1IsSelectable)
            rowText2.setTextIsSelectable(text2IsSelectable)
            rowText3.setTextIsSelectable(text3IsSelectable)
        }
    }

    fun setTextStyle(@StyleRes style: Int, @StyleRes style2: Int = style, @StyleRes style3: Int = style) {
        binding.apply {
            rowText1.setStyle(style)
            rowText2.setStyle(style2)
            rowText3.setStyle(style3)
        }
    }

    fun setText1AsHeading(isHeading: Boolean) {
        binding.rowText1.setAsAccessibilityHeading(isHeading)
    }

    internal fun setTextFocusable(focusable: Boolean) {
        binding.apply {
            rowText1.isFocusable = focusable
            rowText2.isFocusable = focusable
            rowText3.isFocusable = focusable
        }
    }

    companion object {
        private const val MAX_SINGLE_LINE_FONT_SCALE = 1.5
    }
}
