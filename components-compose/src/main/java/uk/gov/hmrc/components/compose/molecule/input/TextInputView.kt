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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object TextInputView {

    @SuppressWarnings("LongMethod", "ComplexMethod")
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        value: String? = null,
        onInputValueChange: ((String) -> Unit)? = null,
        inputFilter: ((String, String) -> String)? = null,
        labelText: String? = null,
        labelContentDescription: String? = null,
        hintText: String? = null,
        hintContentDescription: String? = null,
        prefix: @Composable() (() -> Unit)? = null,
        placeholderText: String? = null,
        errorText: String? = null,
        errorContentDescription: String? = null,
        characterCount: Int? = null,
        maxChars: Int? = null,
        singleLine: Boolean = false,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        requiredSequencesSpacing: Boolean = false
    ) {
        var localValue: String by rememberSaveable { mutableStateOf(value.orEmpty()) }
        localValue = value.orEmpty()

        var localError: String? by rememberSaveable { mutableStateOf(null) }
        localError = errorText

        val counterEnabled: Boolean = characterCount != null

        val clearTrailingIcon: @Composable (() -> Unit) = @Composable {
            if (localValue.isNotEmpty()) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = stringResource(id = R.string.textInputView_clear),
                    modifier = Modifier.clickable {
                        localValue = ""
                        if (onInputValueChange != null) {
                            onInputValueChange(localValue)
                        }
                    }
                )
            }
        }

        Column(modifier = modifier) {
            Label(labelText = labelText, labelContentDescription = labelContentDescription)
            Hint(hintText = hintText, hintContentDescription = hintContentDescription)
            TextField(
                modifier = Modifier.adjustPaddingForCounter(counterEnabled, localError),
                isError = !localError.isNullOrEmpty() || (localValue.length > (characterCount ?: Int.MAX_VALUE)),
                value = localValue,
                onInputValueChange = { newValue ->
                    if (maxChars?.let { newValue.length <= it } != false) {
                        localValue = if (inputFilter != null && newValue.isNotEmpty()) {
                            inputFilter(newValue, localValue)
                        } else newValue
                        if (onInputValueChange != null) { onInputValueChange(localValue) }
                    }
                },
                prefix = prefix,
                placeholderText = { placeholderText?.let { Text(text = it) } },
                supportingText = if (counterEnabled)
                    errorTextCounterCombo(errorText, errorContentDescription, characterCount, localValue)
                else error(
                    errorText,
                    errorContentDescription
                ),
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                visualTransformation = VisualTransformation.None,
                trailingIcon = clearTrailingIcon,
                colors = HmrcTheme.textFieldColors,
                textStyle = if (requiredSequencesSpacing) { HmrcTheme.typography.sequencesBody }
                else { HmrcTheme.typography.body }
            )
        }
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
            labelText = "Label",
            errorText = "Error"
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
            characterCount = 20
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
            characterCount = 20
        )
    }
}
