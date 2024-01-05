/*
 * Copyright 2023 HM Revenue & Customs
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
package uk.gov.hmrc.components.compose.molecule.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

object TextInputView {

    private const val ERROR_TEXT_WITH_CHAR_COUNT_WIDTH = 0.8f
    private const val CHAR_COUNT_WIDTH = 0.95f

    @SuppressWarnings("LongMethod", "ComplexMethod")
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        initialInputValue: String = "",
        onInputValueChange: ((String) -> Unit)? = null,
        inputFilter: ((String, String) -> String)? = null,
        labelText: String? = null,
        labelContentDescription: String? = null,
        hintText: String? = null,
        hintContentDescription: String? = null,
        prefix: @Composable (() -> Unit)? = null,
        placeholderText: String? = null,
        errorText: String? = null,
        errorContentDescription: String? = null,
        characterCount: Int? = null,
        singleLine: Boolean = false,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default
    ) {
        var localValue: String by rememberSaveable { mutableStateOf(initialInputValue) }
        var localError: String? by rememberSaveable { mutableStateOf(null) }
        localError = errorText

        val counterEnabled: Boolean = characterCount != null

        val error: @Composable (() -> Unit)? = errorText?.let {
            @Composable {
                Text(
                    text = it,
                    modifier = Modifier.semantics { errorContentDescription?.let { contentDescription = it } }
                )
            }
        }

        val errorTextCounterCombo: @Composable (() -> Unit) = @Composable {
            Row {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth(ERROR_TEXT_WITH_CHAR_COUNT_WIDTH)
                ) {
                    if (errorText != null) {
                        Text(
                            text = errorText,
                            modifier = Modifier.semantics {
                                errorContentDescription?.let {
                                    contentDescription = it
                                }
                            }
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth(CHAR_COUNT_WIDTH)) {
                    Text(
                        text = "${localValue.length}/$characterCount",
                        textAlign = TextAlign.End,
                    )
                }
            }
        }

        val trailingIcon: @Composable (() -> Unit) = @Composable {
            if (localValue.isNotEmpty()) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = stringResource(id = R.string.textInputView_clear),
                    modifier = Modifier.clickable {
                        localValue = ""
                        if (onInputValueChange != null) { onInputValueChange(localValue) }
                    }
                )
            }
        }

        @Composable
        fun Modifier.adjustPaddingForCounter() = this.padding(
            bottom = if (counterEnabled || !localError.isNullOrEmpty()) 0.dp else HmrcTheme.dimensions.hmrcIconSize24
        )

        Column(modifier = modifier) {
            Label(labelText = labelText, labelContentDescription = labelContentDescription)
            Hint(hintText = hintText, hintContentDescription = hintContentDescription)
            TextField(
                modifier = Modifier.fillMaxWidth().adjustPaddingForCounter(),
                isError = !localError.isNullOrEmpty() || (localValue.length > (characterCount ?: Int.MAX_VALUE)),
                value = localValue,
                onInputValueChange = { newValue ->
                    if (onInputValueChange != null) { onInputValueChange(newValue) }
                    localValue = if (inputFilter != null && newValue.isNotEmpty()) {
                        inputFilter(newValue, localValue)
                    } else newValue
                },
                prefix = prefix,
                placeholderText = { placeholderText?.let { Text(text = it) } },
                supportingText = if (counterEnabled) errorTextCounterCombo else error,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
            )
        }
    }

    @Composable
    private fun Label(labelText: String?, labelContentDescription: String?,) {
        labelText?.let { label ->
            Text(
                text = label,
                style = typography.h6,
                modifier = Modifier
                    .semantics { labelContentDescription?.let { contentDescription = it } }
                    .padding(bottom = HmrcTheme.dimensions.hmrcSpacing8)
            )
        }
    }

    @Composable
    private fun Hint(hintText: String?, hintContentDescription: String?) {
        hintText?.let { hint ->
            Text(
                text = hint,
                style = typography.body,
                modifier = Modifier
                    .semantics { hintContentDescription?.let { contentDescription = it } }
                    .padding(bottom = HmrcTheme.dimensions.hmrcSpacing8)
            )
        }
    }

    @Suppress("LongParameterList")
    @Composable
    private fun TextField(
        modifier: Modifier,
        isError: Boolean,
        value: String,
        onInputValueChange: ((String) -> Unit),
        prefix: @Composable (() -> Unit)?,
        placeholderText: @Composable (() -> Unit),
        supportingText: @Composable (() -> Unit)?,
        trailingIcon: @Composable (() -> Unit),
        singleLine: Boolean,
        keyboardOptions: KeyboardOptions,
    ) {
        OutlinedTextField(
            modifier = modifier,
            isError = isError,
            value = value,
            onValueChange = onInputValueChange,
            prefix = prefix,
            placeholder = placeholderText,
            supportingText = supportingText,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            textStyle = typography.body,
            colors = HmrcTheme.textFieldColors,
            shape = RoundedCornerShape(0)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputViewPreview() {
    HmrcTheme {
        TextInputView(
            onInputValueChange = { },
            labelText = "Label",
            hintText = "Hint",
            placeholderText = "Text"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputViewNoLabelPreview() {
    HmrcTheme {
        TextInputView(
            onInputValueChange = { },
            hintText = "Hint",
            placeholderText = "Text"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputViewWithErrorPreview() {
    HmrcTheme {
        TextInputView(
            onInputValueChange = { },
            errorText = "Error",
            labelText = "Label",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputViewWithCounterPreview() {
    HmrcTheme {
        TextInputView(
            onInputValueChange = { },
            labelText = "Label",
            characterCount = 20,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputViewWithErrorAndCounterPreview() {
    HmrcTheme {
        TextInputView(
            onInputValueChange = { },
            labelText = "Label",
            errorText = "Error",
            characterCount = 20,
        )
    }
}
