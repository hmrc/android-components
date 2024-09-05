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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.hmrc.components.compose.atom.text.LinkText
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun PasswordInputView(
    modifier: Modifier = Modifier,
    value: String? = null,
    onInputValueChange: ((String) -> Unit)? = null,
    labelText: String? = null,
    labelContentDescription: String? = null,
    hintText: String? = null,
    hintContentDescription: String? = null,
    placeholderText: String? = null,
    errorText: String? = null,
    errorContentDescription: String? = null,
    singleLine: Boolean = true,
    numericOnly: Boolean = false,
    maxChars: Int? = null,
) {

    // pattern matches a non decimal number
    val nonDecimalPattern = remember { Regex("^([0-9]*)$") }

    var passwordVisualTransformation: VisualTransformation
        by remember { mutableStateOf(PasswordVisualTransformation()) }

    var passwordBool: Boolean by rememberSaveable { mutableStateOf(false) }

    fun decimalPatternChecker(input: String, localValue: String) = when {
        numericOnly && input.matches(nonDecimalPattern) -> input
        !numericOnly -> input
        else -> localValue
    }

    val passwordTrailingIcon: @Composable (() -> Unit) = @Composable {
        if (passwordBool) {
            LinkText("Hide", modifier = Modifier.clickable { passwordBool = false })
            passwordVisualTransformation = VisualTransformation.None
        } else {
            LinkText("Show", modifier = Modifier.clickable { passwordBool = true })
            passwordVisualTransformation = PasswordVisualTransformation()
        }
    }

    TextInputView(
        modifier = modifier,
        value = value,
        onInputValueChange = onInputValueChange,
        inputFilter = { it: String, localValue: String -> decimalPatternChecker(it, localValue) },
        labelText = labelText,
        labelContentDescription = labelContentDescription,
        hintText = hintText,
        hintContentDescription = hintContentDescription,
        placeholderText = placeholderText,
        errorText = errorText,
        errorContentDescription = errorContentDescription,
        trailingIcon = passwordTrailingIcon,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (numericOnly) KeyboardType.NumberPassword else KeyboardType.Password
        ),
        maxChars = maxChars,
        visualTransformation = passwordVisualTransformation
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputViewPreview() {
    HmrcTheme {
        PasswordInputView(
            onInputValueChange = { },
            labelText = "Label",
            hintText = "Hint",
            placeholderText = "Text"
        )
    }
}
