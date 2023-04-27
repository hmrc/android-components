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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import uk.gov.hmrc.components.compose.ui.theme.textInputViewColors
import uk.gov.hmrc.components.compose.molecule.input.TextInputView

@Composable
fun CurrencyInputView(): TextInputView {

    var localValue: String by remember {
        mutableStateOf("")
    }

    var isErrorLocal: Boolean by remember {
        mutableStateOf(false)
    }

    val counterEnabled: Boolean = characterCount != null

    fun supportingText(): @Composable (() -> Unit)? {
        if (isErrorLocal && (!errorText.isNullOrEmpty())) {
            return { Text(text = errorText) }
        }
        else if (!supportingText.isNullOrEmpty()) {
            return { Text(text = supportingText) }
        }
        return null
    }

    @Composable
    fun supportingTextCounterCombo(): @Composable (() -> Unit)? {
        return {
            Row() {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth(0.8f)) {
                    if (isErrorLocal && (!errorText.isNullOrEmpty())) {
                        Text(text = errorText)
                    } else if (!supportingText.isNullOrEmpty()) {
                        Text(text = supportingText)
                    }
                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth(0.95f)) {
                    Text(
                        text = "${localValue.length}/$characterCount",
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
    }

    Row(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            isError = isErrorLocal,
            value = localValue,
            onValueChange = {
                localValue = it
                vm.updateValue(localValue)
                isErrorLocal = isError.invoke()
            },
            colors = textInputViewColors(),
            label = { labelText?.let { Text(text = it) } },
            supportingText = if (counterEnabled) supportingTextCounterCombo() else supportingText(),
            placeholder = { placeholderText?.let { Text(text = it) } },
            singleLine = singleLine,
            trailingIcon = {
                if(isErrorLocal) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "error"
                    )
                }
                else {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                localValue = ""
                            }
                    )
                }
            }
        )
    }
}




