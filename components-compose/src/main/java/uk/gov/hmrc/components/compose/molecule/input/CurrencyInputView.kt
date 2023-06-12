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

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CurrencyInputView(
    modifier: Modifier = Modifier,
    onInputValueChange: ((String) -> Unit)? = null,
    placeholderText: String? = null,
    errorText: String? = null,
    errorContentDescription: String? = null,
    labelText: String? = null,
    labelContentDescription: String? = null,
    singleLine: Boolean = false,
    characterCount: Int? = null,
    enableDecimal: Boolean = true,
) {

    // pattern matches a decimal number
    val decimalPattern = remember { Regex("^(?!0[0-9])[0-9]+(\\.?)([0-9]?[0-9]?)") }
    // pattern matches a non decimal number
    val nonDecimalPattern = remember { Regex("^(?!0[0-9])[0-9]+$") }

    fun decimalPatternChecker(input: String, localValue: String): (String) {
        val matchesDecimal: Boolean = input.matches(decimalPattern)

        return if (enableDecimal) {
            if (matchesDecimal) {
                input.filter { input.matches(decimalPattern) }
            } else localValue
        } else input.filter { input.matches(nonDecimalPattern) }
    }

    TextInputView(
        modifier,
        onInputValueChange,
        inputFilter = { it: String, localValue: String -> decimalPatternChecker(it, localValue) },
        placeholderText,
        errorText,
        errorContentDescription,
        labelText,
        labelContentDescription,
        singleLine,
        characterCount,
        prefix = { Text(text = "£") },
        keyboardOptions = if (enableDecimal) KeyboardOptions(keyboardType = KeyboardType.Decimal)
        else KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
