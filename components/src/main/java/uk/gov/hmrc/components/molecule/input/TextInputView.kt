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
package uk.gov.hmrc.components.molecule.input

import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.os.Parcelable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentTextInputBinding

open class TextInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    private val binding: ComponentTextInputBinding =
            ComponentTextInputBinding.inflate(LayoutInflater.from(context), this, true)

    private var editTextId = -1
        set(value) {
            field = value
            getEditText().id = value
        }

    private var layoutId = -1
        set(value) {
            field = value
            binding.root.id = value
        }

    private var hintContentDescription: String? = null

    init {

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.TextInputView, 0, 0)

            val textString = typedArray.getString(R.styleable.TextInputView_text) ?: ""
            val hintTextContentDescription = typedArray.getString(R.styleable.TextInputView_hintContentDescription)
            val hintText = typedArray.getString(R.styleable.TextInputView_hintText) ?: ""
            val errorText = typedArray.getString(R.styleable.TextInputView_errorText) ?: ""
            val counterMaxLength = typedArray.getInt(R.styleable.TextInputView_counterMaxLength, NO_MAX_LENGTH)
            val counterEnabled = typedArray.getBoolean(R.styleable.TextInputView_counterEnabled, false)
            val imeOptions = typedArray.getInt(R.styleable.TextInputView_android_imeOptions, getEditText().imeOptions)
            val inputType = typedArray.getInt(R.styleable.TextInputView_android_inputType, getEditText().inputType)
            val maxLines = typedArray.getInt(R.styleable.TextInputView_android_maxLines, getEditText().maxLines)
            val maxLength = typedArray.getInt(R.styleable.TextInputView_android_maxLength, -1)

            setText(textString)
            setHint(hintText, hintTextContentDescription)
            setError(errorText)
            setCounterMaxLength(counterMaxLength)
            setCounterEnabled(counterEnabled)
            setImeOptions(imeOptions)
            setInputType(inputType)
            setMaxLength(maxLength)
            setMaxLines(maxLines)

            typedArray.recycle()
        }
        editTextId = View.generateViewId()
        layoutId = View.generateViewId()
    }

    override fun onSaveInstanceState(): Parcelable? {
        return Bundle().apply {
            putParcelable(STATE_SUPER, super.onSaveInstanceState())
            putInt(STATE_EDIT_TEXT_ID, editTextId)
            putInt(STATE_LAYOUT_ID, layoutId)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? Bundle)?.let {
            editTextId = it.getInt(STATE_EDIT_TEXT_ID)
            layoutId = it.getInt(STATE_LAYOUT_ID)
            super.onRestoreInstanceState(it.getParcelable(STATE_SUPER))
        }
    }

    fun setText(string: CharSequence?) {
        getEditText().setText(string)
    }

    fun getText(): String? = getEditText().text?.toString()

    fun setHint(hint: CharSequence, contentDescription: CharSequence? = null) {
        binding.root.hint = hint
        hintContentDescription = (contentDescription ?: hint).toString()
        updateTextInputViewContentDescription()
    }

    fun getError() = binding.root.error

    fun setError(errorText: CharSequence?, errorContentDescription: CharSequence? = null) {
        binding.root.error = errorText
        binding.root.errorContentDescription = errorContentDescription ?: if (!errorText.isNullOrEmpty()) {
            context.getString(R.string.accessibility_error_prefix, errorText)
        } else ""
        updateTextInputViewContentDescription()
    }

    fun setErrorText(@StringRes error: Int?, @StringRes errorContentDescription: Int? = null) {
        setError(
            errorText = if (error == null) null else context.getString(error),
            errorContentDescription = if (errorContentDescription == null) {
                null
            } else context.getString(errorContentDescription)
        )
    }

    fun setCounterMaxLength(maxLength: Int) {
        binding.root.counterMaxLength = maxLength
        updateTextInputViewContentDescription()
    }

    fun setCounterEnabled(enabled: Boolean) {
        binding.root.isCounterEnabled = enabled
        updateTextInputViewContentDescription()
    }

    fun setPrefixText(prefixText: CharSequence?) {
        binding.root.prefixText = prefixText
    }

    fun setImeOptions(imeOptions: Int) {
        getEditText().imeOptions = imeOptions
    }

    fun setInputType(inputType: Int) {
        getEditText().inputType = inputType
    }

    fun setMaxLength(maxLength: Int) {
        if (maxLength != -1) {
            getEditText().filters = getEditText().filters.toMutableList().apply {
                add(InputFilter.LengthFilter(maxLength))
            }.toTypedArray()
        }
    }

    fun setMaxLines(maxLines: Int) {
        getEditText().maxLines = maxLines
    }

    fun getEditText(): TextInputEditText = binding.root.findViewWithTag("edit_text")

    private fun updateTextInputViewContentDescription() {
        binding.root.apply {
            setTextInputAccessibilityDelegate(object : TextInputLayout.AccessibilityDelegate(this) {
                override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(host, info)

                    val customHint = hintContentDescription ?: hint

                    val errorContentDescription = errorContentDescription
                    val error = if (errorContentDescription.isNullOrEmpty()) "" else ", $errorContentDescription"

                    val counter = if (isCounterEnabled) {
                        val currentChars = if (getText().isNullOrEmpty()) 0 else getText()!!.length
                        val maxLength = counterMaxLength

                        val limitExceededText = if (currentChars > maxLength) {
                            "${context.getString(R.string.accessibility_counter_limit_exceeded)} "
                        } else ""

                        val counterText = context.getString(
                            R.string.accessibility_counter_state,
                            currentChars.toString(),
                            maxLength.toString())

                        ", $limitExceededText$counterText"
                    } else ""

                    val newContentDescription = "$customHint$error$counter"

                    val showingText = !getText().isNullOrEmpty()
                    if (VERSION.SDK_INT >= O) {
                        if (showingText) {
                            info.hintText = newContentDescription
                        } else {
                            info.text = newContentDescription
                        }
                    } else {
                        // Due to a TalkBack bug, setHintText has no effect in APIs < 26 so we append the hint to
                        // the text announcement. The resulting announcement is the same as in APIs >= 26.
                        info.text = if (showingText) {
                            getText() + ", " + newContentDescription
                        } else newContentDescription
                    }
                }
            })
        }
    }

    companion object {
        private const val STATE_TEXT = "STATE_TEXT"
        private const val STATE_SUPER = "STATE_SUPER"
        private const val STATE_EDIT_TEXT_ID = "STATE_EDIT_TEXT_ID"
        private const val STATE_LAYOUT_ID = "STATE_LAYOUT_ID"
        const val NO_MAX_LENGTH = 0
        const val DRAWABLE_LEFT = 0
        const val DRAWABLE_TOP = 1
        const val DRAWABLE_RIGHT = 2
        const val DRAWABLE_BOTTOM = 3
    }
}
