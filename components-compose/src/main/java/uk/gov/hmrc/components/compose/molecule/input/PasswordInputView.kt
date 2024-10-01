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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.password
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.button.SecondaryButton
import uk.gov.hmrc.components.compose.atom.text.ErrorText
import uk.gov.hmrc.components.compose.molecule.input.PasswordInput.ANIMATION_DURATION
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

@Composable
fun PasswordInputView(
    modifier: Modifier = Modifier,
    passwordTrailingButton: PasswordTrailingButton,
    value: String? = null,
    onInputValueChange: ((String) -> Unit)? = null,
    labelText: String? = null,
    labelContentDescription: String? = null,
    hintText: String? = null,
    hintContentDescription: String? = null,
    errorText: String? = null,
    errorContentDescription: String? = null,
    numericOnly: Boolean = true,
    maxChars: Int? = null,
    requiredSequencesSpacing: Boolean = true,
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
        errorText = errorText,
        errorContentDescription = errorContentDescription,
        passwordTrailingButton = PasswordTrailingButton(
            showButtonText = passwordTrailingButton.showButtonText,
            showButtonContentDescription = passwordTrailingButton.showButtonContentDescription,
            showButtonStateDescription = passwordTrailingButton.showButtonStateDescription,
            hideButtonText = passwordTrailingButton.hideButtonText,
            hideButtonContentDescription = passwordTrailingButton.hideButtonContentDescription,
            hideButtonStateDescription = passwordTrailingButton.hideButtonStateDescription
        ),
        maxChars = maxChars,
        numericOnly = numericOnly,
        requiredSequencesSpacing = requiredSequencesSpacing
    )
}

@Composable
private fun animateBorderStrokeAsState(
    isError: Boolean,
    interactionSource: InteractionSource,
): State<BorderStroke> {
    val focused by interactionSource.collectIsFocusedAsState()
    val indicatorColor = indicatorColor(isError, interactionSource)
    val targetThickness = if (focused) FocusedBorderThickness else UnfocusedBorderThickness
    val animatedThickness =
        animateDpAsState(targetThickness, tween(durationMillis = ANIMATION_DURATION))
    return rememberUpdatedState(
        BorderStroke(animatedThickness.value, SolidColor(indicatorColor.value))
    )
}

@Composable
private fun indicatorColor(
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
    return animateColorAsState(targetValue, tween(durationMillis = ANIMATION_DURATION))
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
    errorText: String? = null,
    errorContentDescription: String? = null,
    passwordTrailingButton: PasswordTrailingButton,
    numericOnly: Boolean,
    characterCount: Int? = null,
    maxChars: Int? = null,
    requiredSequencesSpacing: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
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
        hideButtonContentDescription: String?,
        hideButtonStateDescription: String?,
        showButtonContentDescription: String?,
        showButtonStateDescription: String?,
        showButtonText: String,
        hideButtonText: String
    ) {
        val activate = stringResource(R.string.accessibility_activate)

        if (hideButtonShown) {
            SecondaryButton(
                hideButtonText, onClick = { hideButtonShown = false },
                modifier = Modifier
                    .clearAndSetSemantics {
                        contentDescription = hideButtonContentDescription ?: hideButtonText
                        hideButtonStateDescription?.let { stateDescription = it }
                        role = Role.Button
                        onClick(activate, action = { hideButtonShown = false; true })
                    }
            )
        } else {
            SecondaryButton(
                showButtonText, onClick = { hideButtonShown = true },
                modifier = Modifier
                    .clearAndSetSemantics {
                        contentDescription = showButtonContentDescription ?: showButtonText
                        showButtonStateDescription?.let { stateDescription = it }
                        role = Role.Button
                        onClick(activate, action = { hideButtonShown = true; true })
                    }
            )
        }
    }

    val borderStroke: State<BorderStroke> = animateBorderStrokeAsState(
        isError,
        interactionSource,
    )
    Column(modifier = modifier) {
        Label(labelText = labelText, labelContentDescription = labelContentDescription)
        Hint(hintText = hintText, hintContentDescription = hintContentDescription)
        Row(
            borderStroke.let {
                Modifier
                    .fillMaxWidth()
                    .border(it.value, RectangleShape)
            },
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
                    prefix = null,
                    placeholderText = null,
                    supportingText = null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = if (numericOnly) KeyboardType.NumberPassword else KeyboardType.Password
                    ),
                    visualTransformation = if (hideButtonShown) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    textStyle =
                    if (requiredSequencesSpacing && hideButtonShown) {
                        HmrcTheme.typography.sequencesBody
                    } else {
                        HmrcTheme.typography.body
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        errorBorderColor = HmrcTheme.colors.hmrcTransparent,
                        unfocusedBorderColor = HmrcTheme.colors.hmrcTransparent,
                        focusedBorderColor = HmrcTheme.colors.hmrcTransparent
                    ),
                    interactionSource = interactionSource
                )
            }

            Column(Modifier.width(IntrinsicSize.Max)) {
                ShowHideButton(
                    hideButtonContentDescription = passwordTrailingButton.hideButtonContentDescription,
                    hideButtonStateDescription = passwordTrailingButton.hideButtonStateDescription,
                    showButtonContentDescription = passwordTrailingButton.showButtonContentDescription,
                    showButtonStateDescription = passwordTrailingButton.showButtonStateDescription,
                    showButtonText = passwordTrailingButton.showButtonText,
                    hideButtonText = passwordTrailingButton.hideButtonText
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

data class PasswordTrailingButton(
    val showButtonText: String,
    val showButtonContentDescription: String? = null,
    val showButtonStateDescription: String? = null,
    val hideButtonText: String,
    val hideButtonContentDescription: String? = null,
    val hideButtonStateDescription: String? = null
)

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
            passwordTrailingButton = PasswordTrailingButton(
                showButtonText = "Show",
                hideButtonText = "Hide"
            )
        )
    }
}
