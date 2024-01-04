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

@Composable
@Suppress("ComplexMethod")
fun TextInputView(
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

    val errorTextCounterCombo: @Composable (() -> Unit) =
        @Composable {
            Row {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth(ErrorTextWithCharCountWidth)
                ) {
                    if (errorText != null) {
                        Text(
                            text = errorText,
                            modifier = Modifier.semantics { errorContentDescription?.let { contentDescription = it } }
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth(CharCountWidth)) {
                    Text(
                        text = "${localValue.length}/$characterCount",
                        textAlign = TextAlign.End,
                    )
                }
            }
        }

    @Composable
    fun Modifier.adjustPaddingForCounter() = this.padding(
        bottom = if (counterEnabled || !localError.isNullOrEmpty()) 0.dp else HmrcTheme.dimensions.hmrcIconSize24
    )

    Column(modifier = modifier) {
        labelText?.let { label ->
            Text(
                text = label,
                style = typography.h6,
                modifier = Modifier
                    .semantics { labelContentDescription?.let { contentDescription = it } }
                    .padding(bottom = HmrcTheme.dimensions.hmrcSpacing8)
            )
        }
        hintText?.let { hint ->
            Text(
                text = hint,
                style = typography.body,
                modifier = Modifier
                    .semantics { hintContentDescription?.let { contentDescription = it } }
                    .padding(bottom = HmrcTheme.dimensions.hmrcSpacing8)
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().adjustPaddingForCounter(),
            isError = !localError.isNullOrEmpty() || (localValue.length > (characterCount ?: Int.MAX_VALUE)),
            value = localValue,
            onValueChange = { newValue ->
                if (onInputValueChange != null) {
                    onInputValueChange(newValue)
                }
                localValue = if (inputFilter != null && newValue.isNotEmpty()) {
                    inputFilter(newValue, localValue)
                } else newValue
            },
            textStyle = typography.body,
            colors = HmrcTheme.textFieldColors,
            supportingText = if (counterEnabled) errorTextCounterCombo else error,
            placeholder = { placeholderText?.let { Text(text = it) } },
            singleLine = singleLine,
            trailingIcon = {
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
            },
            prefix = prefix,
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(0)
        )
    }
}

private const val ErrorTextWithCharCountWidth = 0.8f
private const val CharCountWidth = 0.95f

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
