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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.atom.text.BoldText
import uk.gov.hmrc.components.compose.atom.text.ErrorText
import uk.gov.hmrc.components.compose.molecule.input.CommonInputView.CHAR_COUNT_WIDTH
import uk.gov.hmrc.components.compose.molecule.input.CommonInputView.ERROR_TEXT_WITH_CHAR_COUNT_WIDTH
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

@Suppress("LongParameterList")
@Composable
fun TextField(
    modifier: Modifier,
    isError: Boolean,
    value: String,
    onInputValueChange: (String) -> Unit,
    prefix: @Composable() (() -> Unit)?,
    placeholderText: @Composable () -> Unit,
    supportingText: @Composable() (() -> Unit)?,
    singleLine: Boolean,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable() (() -> Unit)? = null,
    colors: TextFieldColors,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
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
        colors = colors,
        shape = RoundedCornerShape(0),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource
    )
}

@Composable
fun Label(labelText: String?, labelContentDescription: String?,) {
    labelText?.let { label ->
        BoldText(
            text = label,
            modifier = Modifier
                .semantics { labelContentDescription?.let { contentDescription = it } }
                .padding(bottom = HmrcTheme.dimensions.hmrcSpacing8)
        )
    }
}

@Composable
fun Hint(hintText: String?, hintContentDescription: String?) {
    hintText?.let { hint ->
        BodyText(
            text = hint,
            modifier = Modifier
                .semantics { hintContentDescription?.let { contentDescription = it } }
                .padding(bottom = HmrcTheme.dimensions.hmrcSpacing8)
        )
    }
}

fun error(errorText: String?, errorContentDescription: String?):
    @Composable (() -> Unit)? = errorText?.let {
    @Composable {
        ErrorText(
            text = it,
            modifier = Modifier.semantics {
                errorContentDescription?.let {
                    contentDescription = it
                }
            }
        )
    }
}

fun errorTextCounterCombo(
    errorText: String?,
    errorContentDescription: String?,
    characterCount: Int?,
    localValue: String
):
    @Composable (() -> Unit) = @Composable {
    Row {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(ERROR_TEXT_WITH_CHAR_COUNT_WIDTH)
        ) {
            if (errorText != null) {
                ErrorText(
                    text = errorText,
                    modifier = Modifier.semantics {
                        errorContentDescription?.let {
                            contentDescription = it
                        }
                    }
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth(CHAR_COUNT_WIDTH)
        ) {
            val textStyle = characterCount?.let { limit ->
                if (localValue.length > limit) typography.errorText else typography.body
            } ?: typography.body
            Text(
                text = "${localValue.length}/$characterCount",
                style = textStyle,
                textAlign = TextAlign.End,
            )
        }
    }
}

object CommonInputView {
    const val ERROR_TEXT_WITH_CHAR_COUNT_WIDTH = 0.8f
    const val CHAR_COUNT_WIDTH = 0.95f
}

@Composable
fun Modifier.adjustPaddingForCounter(counterEnabled: Boolean, localError: String?) = this.padding(
    bottom = if (counterEnabled || !localError.isNullOrEmpty()) 0.dp else HmrcTheme.dimensions.hmrcIconSize24
)
