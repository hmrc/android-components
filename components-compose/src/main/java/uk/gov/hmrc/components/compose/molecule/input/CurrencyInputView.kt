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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun CurrencyInputView(
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
    enableDecimal: Boolean = true,
    maxChars: Int? = null,
) {

    // pattern matches a decimal number
    val decimalPattern = remember { Regex("^([0-9]*)(\\.?)([0-9]*)$") }
    // pattern matches a non decimal number
    val nonDecimalPattern = remember { Regex("^([0-9]*)$") }

    fun decimalPatternChecker(input: String, localValue: String) = when {
        enableDecimal && input.matches(decimalPattern) -> input
        !enableDecimal && input.matches(nonDecimalPattern) -> input
        else -> localValue
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
        prefix = {
            BodyText(
                text = "£",
                modifier = Modifier.padding(end = HmrcTheme.dimensions.hmrcSpacing8)
            )
        },
        placeholderText = placeholderText,
        errorText = errorText,
        errorContentDescription = errorContentDescription,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (enableDecimal) KeyboardType.Decimal else KeyboardType.Number
        ),
        maxChars = maxChars
    )
}

@Preview(showBackground = true)
@Composable
fun CurrencyInputViewPreview() {
    HmrcTheme {
        CurrencyInputView(
            onInputValueChange = { },
            labelText = "Label",
            hintText = "Hint",
            placeholderText = "Text"
        )
    }
}
