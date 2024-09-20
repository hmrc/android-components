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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.FocusedBorderThickness
import androidx.compose.material3.OutlinedTextFieldDefaults.UnfocusedBorderThickness
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.password
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.button.SecondaryButton
import uk.gov.hmrc.components.compose.atom.text.ErrorText
import uk.gov.hmrc.components.compose.molecule.input.PasswordInput.ANIMATION_DURATION
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
    showButtonContentDescription: String? = null,
    hideButtonContentDescription: String? = null,
    showButtonStateDescription: String? = null,
    hideButtonStateDescription: String? = null,
    singleLine: Boolean = true,
    numericOnly: Boolean = false,
    maxChars: Int? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },

) {
    // pattern matches a non decimal number
    val nonDecimalPattern = remember { Regex("^([0-9]*)$") }

    fun decimalPatternChecker(input: String, localValue: String) = when {
        numericOnly && input.matches(nonDecimalPattern) -> input
        !numericOnly -> input
        else -> localValue
    }

    PasswordTextInputView(
        modifier = modifier.semantics { password() },
        value = value,
        onInputValueChange = onInputValueChange,
        inputFilter = { it: String, localValue: String ->
            decimalPatternChecker(
                it,
                localValue
            )
        },
        labelText = labelText,
        labelContentDescription = labelContentDescription,
        hintText = hintText,
        hintContentDescription = hintContentDescription,
        placeholderText = placeholderText,
        errorText = errorText,
        errorContentDescription = errorContentDescription,
        showButtonContentDescription = showButtonContentDescription,
        hideButtonContentDescription = hideButtonContentDescription,
        showButtonStateDescription = showButtonStateDescription,
        hideButtonStateDescription = hideButtonStateDescription,
        maxChars = maxChars,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (numericOnly) KeyboardType.NumberPassword else KeyboardType.Password
        ),
        interactionSource = interactionSource
    )
}

@Composable
fun animateBorderStrokeAsState(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    focusedBorderThickness: Dp,
    unfocusedBorderThickness: Dp
): State<BorderStroke> {
    val focused by interactionSource.collectIsFocusedAsState()
    val indicatorColor = indicatorColor(enabled, isError, interactionSource)
    val targetThickness = if (focused) focusedBorderThickness else unfocusedBorderThickness
    val animatedThickness = if (enabled) {
        animateDpAsState(targetThickness, tween(durationMillis = ANIMATION_DURATION))
    } else {
        rememberUpdatedState(unfocusedBorderThickness)
    }
    return rememberUpdatedState(
        BorderStroke(animatedThickness.value, SolidColor(indicatorColor.value))
    )
}

var borderStroke: State<BorderStroke>? = null

@Composable
fun CalculateBorder(
    enabled: Boolean = true,
    isError: Boolean,
    interactionSource: InteractionSource,
    focusedBorderThickness: Dp = FocusedBorderThickness,
    unfocusedBorderThickness: Dp = UnfocusedBorderThickness
) {
    borderStroke = animateBorderStrokeAsState(
        enabled,
        isError,
        interactionSource,
        focusedBorderThickness,
        unfocusedBorderThickness
    )
}

@Composable
fun indicatorColor(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource
): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    val colors = HmrcTheme.colors

    val focusedBorderColor = colors.hmrcBlue
    val errorBorderColor = colors.hmrcBlack
    val unfocusedBorderColor = colors.hmrcBlack

    val targetValue = when {
        isError -> errorBorderColor
        focused -> focusedBorderColor
        else -> unfocusedBorderColor
    }
    return if (enabled) {
        animateColorAsState(targetValue, tween(durationMillis = ANIMATION_DURATION))
    } else {
        rememberUpdatedState(targetValue)
    }
}

@SuppressWarnings("LongMethod", "ComplexMethod")
@Composable
fun PasswordTextInputView(
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
    showButtonText: String? = stringResource(R.string.passwordTextInputView_show),
    hideButtonText: String? = stringResource(R.string.passwordTextInputView_hide),
    showButtonContentDescription: String? = null,
    hideButtonContentDescription: String? = null,
    showButtonStateDescription: String? = null,
    hideButtonStateDescription: String? = null,
    characterCount: Int? = null,
    maxChars: Int? = null,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource
) {
    var hideButtonShown: Boolean by rememberSaveable { mutableStateOf(false) }

    var localValue: String by rememberSaveable { mutableStateOf(value.orEmpty()) }
    localValue = value.orEmpty()

    var localError: String? by rememberSaveable { mutableStateOf(null) }
    localError = errorText

    var isError: Boolean by rememberSaveable { mutableStateOf(false) }

    isError = !localError.isNullOrEmpty() || (localValue.length > (characterCount ?: Int.MAX_VALUE))

    @Composable
    fun ShowHideButton(
        modifier: Modifier,
        hideButtonContentDescription: String?,
        hideButtonStateDescription: String?,
        showButtonContentDescription: String?,
        showButtonStateDescription: String?,
        showButtonText: String,
        hideButtonText: String
    ) {
        if (hideButtonShown) {
            SecondaryButton(
                hideButtonText, onClick = { hideButtonShown = false },
                modifier = modifier
                    .semantics {
                        hideButtonContentDescription?.let { contentDescription = it }
                        hideButtonStateDescription?.let { stateDescription = it }
                    }
            )
        } else {
            SecondaryButton(
                showButtonText, onClick = { hideButtonShown = true },
                modifier = modifier
                    .semantics {
                        showButtonContentDescription?.let { contentDescription = it }
                        showButtonStateDescription?.let { stateDescription = it }
                    }
            )
        }
    }

    CalculateBorder(
        true,
        isError,
        interactionSource,
        focusedBorderThickness = FocusedBorderThickness,
        unfocusedBorderThickness = UnfocusedBorderThickness
    )
    Column(modifier = modifier) {
        Label(labelText = labelText, labelContentDescription = labelContentDescription)
        Hint(hintText = hintText, hintContentDescription = hintContentDescription)
        Row(
            borderStroke?.let {
                Modifier
                    .fillMaxWidth()
                    .border(it.value, RectangleShape)
            } ?: Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                TextField(
                    modifier = if (isError) Modifier.clearAndSetSemantics {
                        localError?.let {
                            error(
                                "$it. $errorContentDescription"
                            )
                        }
                    } else Modifier,
                    isError = isError,
                    value = localValue,
                    onInputValueChange = { newValue ->
                        if (maxChars?.let { newValue.length <= it } != false) {
                            localValue = if (inputFilter != null && newValue.isNotEmpty()) {
                                inputFilter(newValue, localValue)
                            } else newValue
                            if (onInputValueChange != null) {
                                onInputValueChange(localValue)
                            }
                        }
                    },
                    prefix = prefix,
                    placeholderText = { placeholderText?.let { Text(text = it) } },
                    supportingText = null,
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    visualTransformation = if (hideButtonShown) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        errorBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    ),
                    interactionSource = interactionSource
                )
            }

            Column(Modifier.width(IntrinsicSize.Max)) {
                ShowHideButton(
                    Modifier,
                    hideButtonContentDescription,
                    hideButtonStateDescription,
                    showButtonContentDescription,
                    showButtonStateDescription,
                    showButtonText ?: stringResource(R.string.passwordTextInputView_show),
                    hideButtonText ?: stringResource(R.string.passwordTextInputView_hide)
                )
            }
        }
        if (isError) {
            localError?.let {
                ErrorText(
                    text = it,
                    modifier = Modifier.padding(
                        start = HmrcTheme.dimensions.hmrcSpacing16,
                        top = HmrcTheme.dimensions.hmrcSpacing4
                    )
                )
            }
        }
    }
}

object PasswordInput {
    const val ANIMATION_DURATION = 150
}

@Preview(showBackground = true)
@Composable
fun PasswordInputViewPreview() {
    HmrcTheme {
        PasswordInputView(
            onInputValueChange = { },
            labelText = "Label",
            hintText = "Hint",
            placeholderText = "Text",
        )
    }
}
