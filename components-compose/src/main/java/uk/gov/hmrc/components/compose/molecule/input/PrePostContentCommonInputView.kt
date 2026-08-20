/*
 * Copyright 2024 HM Revenue & Customs
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

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.atom.text.ErrorText
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongParameterList")
@Composable
internal fun PrePostContentTextField(
    modifier: Modifier,
    isError: Boolean,
    value: String,
    onInputValueChange: (String) -> Unit,
    prefix: @Composable() (() -> Unit)?,
    placeholderText: @Composable (() -> Unit)?,
    supportingText: @Composable() (() -> Unit)?,
    singleLine: Boolean,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    colors: TextFieldColors,
    trailingIcon: @Composable() (() -> Unit)? = null,
    textStyle: TextStyle = typography.body,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isCustomErrorInputHandle: Boolean,
    leadingContent: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    currencyErrorText: String? = null,
    localError: String? = null,
    counterEnabled: Boolean = false,
    isContentPre: Boolean = true
) {
    var customIsError = isError
    val customColors = if (isCustomErrorInputHandle && isError) {
        customIsError = false
        customTextInputViewColors(colors, isError)
    } else {
        colors
    }
    val width = if (leadingContent == "%") dimensions.halfWidth else dimensions.fullWidth
    Row(
        modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth(width)
    ) {
        Column {
            if (leadingContent != null && isContentPre) {
                PreOrPostBox(
                    leadingContent = leadingContent,
                    counterEnabled = counterEnabled,
                    localError = localError,
                    isContentPre = isContentPre
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                isError = customIsError,
                value = value,
                onValueChange = onInputValueChange,
                prefix = prefix,
                placeholder = placeholderText,
                supportingText =
                if (leadingContent != null) { null } else { supportingText },
                trailingIcon = trailingIcon,
                singleLine = true,
                keyboardOptions = keyboardOptions,
                textStyle = textStyle,
                colors = customColors,
                shape = RoundedCornerShape(0),
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                leadingIcon = leadingIcon
            )
        }
        Column {
            if (leadingContent != null && !isContentPre) {
                PreOrPostBox(
                    leadingContent = leadingContent,
                    counterEnabled = counterEnabled,
                    localError = localError,
                    isContentPre = isContentPre
                )
            }
        }
    }
    if (leadingContent != null) {
        SeperateErrorBar(currencyErrorText ?: "")
    }
}

@Composable
fun SeperateErrorBar(errorText: String) {
    if (errorText != "") {
        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(top = dimensions.hmrcSpacing8, bottom = dimensions.hmrcSpacing8)
        ) {
            ErrorText(errorText)
        }
    } else {
        Spacer(
            modifier = Modifier.height(dimensions.hmrcSpacing16)
        )
    }
}
@Composable
fun PreOrPostBox(
    leadingContent: String,
    counterEnabled: Boolean,
    localError: String?,
    isContentPre: Boolean
) {
    val color = HmrcTheme.colors.hmrcBlack
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(52.dp)
            .adjustPaddingForCounter(counterEnabled, localError)
            .background(HmrcTheme.colors.hmrcGrey3)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val half = strokeWidth / 2
                // Top
                drawLine(
                    color = color, start = Offset(half, half),
                    end = Offset(size.width - half, half), strokeWidth = strokeWidth
                )
                // Middle section - border on left or right
                if (isContentPre) {
                    drawLine(
                        color = color,
                        start = Offset(half, half),
                        end = Offset(half, size.height - half),
                        strokeWidth = strokeWidth
                    )
                } else {
                    drawLine(
                        color = color,
                        start = Offset(size.width - half, half),
                        end = Offset(size.width - half, size.height - half),
                        strokeWidth = strokeWidth
                    )
                }
                // Bottom
                drawLine(
                    color = color, start = Offset(half, size.height - half),
                    end = Offset(size.width - half, size.height - half), strokeWidth = strokeWidth
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = leadingContent,
            style = MaterialTheme.typography.titleLarge,
            color = HmrcTheme.colors.hmrcBlack
        )
    }
}
