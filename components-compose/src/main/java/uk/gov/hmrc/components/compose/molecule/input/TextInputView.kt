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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
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
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

@Composable
@Suppress("ComplexMethod")
fun TextInputView(
    modifier: Modifier = Modifier,
    onInputValueChange: ((String) -> Unit)? = null,
    inputFilter: ((String, String) -> String)? = null,
    placeholderText: String? = null,
    errorText: String? = null,
    errorContentDescription: String? = null,
    labelText: String? = null,
    labelContentDescription: String? = null,
    singleLine: Boolean = false,
    characterCount: Int? = null,
    prefix: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {

    var localValue: String by rememberSaveable { mutableStateOf("") }

    var localError: String? by rememberSaveable { mutableStateOf(null) }

    localError = errorText

    val counterEnabled: Boolean = characterCount != null

    fun errorText(): @Composable (() -> Unit)? {
        return errorText?.let {
            {
                Text(
                    text = it,
                    modifier = Modifier.semantics {
                        if (errorContentDescription != null) {
                            contentDescription = errorContentDescription
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun errorTextCounterCombo(): @Composable (() -> Unit) {
        return {
            Row {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth(ErrorTextWithCharCountWidth)
                ) {
                    if (errorText != null) {
                        Text(
                            text = errorText,
                            modifier = Modifier.semantics {
                                if (errorContentDescription != null) {
                                    contentDescription = errorContentDescription
                                }
                            }
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
    }

    @Composable
    fun modifier(): Modifier {
        return if (counterEnabled || !localError.isNullOrEmpty()) {
            modifier
                .fillMaxWidth()
                .padding(top = HmrcTheme.dimensions.hmrcIconSize24)
                .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
        } else {
            modifier
                .fillMaxWidth()
                .padding(vertical = HmrcTheme.dimensions.hmrcIconSize24)
                .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
        }
    }

    OutlinedTextField(
        modifier = modifier(),
        isError = !localError.isNullOrEmpty() || (localValue.length > (characterCount ?: Int.MAX_VALUE)),
        value = localValue,
        onValueChange = {
            if (onInputValueChange != null) {
                onInputValueChange(it)
            }
            localValue = if (inputFilter != null && it.isNotEmpty()) {
                inputFilter(it, localValue)
            } else it
        },
        textStyle = typography.body,
        colors = HmrcTheme.textFieldColors,
        label = {
            labelText?.let {
                Text(
                    text = it,
                    modifier = Modifier.semantics {
                        if (labelContentDescription != null) {
                            contentDescription = labelContentDescription
                        }
                    }
                )
            }
        },
        supportingText = if (counterEnabled) errorTextCounterCombo() else errorText(),
        placeholder = { placeholderText?.let { Text(text = it) } },
        singleLine = singleLine,
        trailingIcon = {
            if (!localError.isNullOrEmpty()) {
                Icon(Icons.Default.Info, contentDescription = stringResource(id = R.string.textInputView_error))
            } else if (localValue.isNotEmpty()) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = stringResource(id = R.string.textInputView_clear),
                    modifier = Modifier.clickable { localValue = "" }
                )
            }
        },
        prefix = prefix,
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(0)
    )
}

private const val ErrorTextWithCharCountWidth = 0.8f
private const val CharCountWidth = 0.95f
