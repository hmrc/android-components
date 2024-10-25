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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.gov.hmrc.components.compose.molecule.input.PasswordInputView
import uk.gov.hmrc.components.compose.molecule.input.PasswordTrailingButton
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.PasswordInputViewModel

@Composable
fun PasswordInputViewScreen() {

    val viewModel = viewModel<PasswordInputViewModel>()

    val errorText = stringResource(id = R.string.password_input_example_error)
    val error1Text = stringResource(id = R.string.password_input_example_1_error)
    val showText = stringResource(R.string.password_input_show)
    val hideText = stringResource(R.string.password_input_hide)

    var placeholderValue: String by rememberSaveable { mutableStateOf("") }
    var example1Value: String by rememberSaveable { mutableStateOf("") }
    var example2Value: String by rememberSaveable { mutableStateOf("") }
    var example3Value: String by rememberSaveable { mutableStateOf("Password123") }

    ScreenScrollViewColumn {
        PlaceholderSlot {
            PasswordInputView(
                value = placeholderValue,
                onInputValueChange = { placeholderValue = it },
                labelText = stringResource(id = R.string.password_input_placeholder_label),
                hintText = stringResource(id = R.string.password_input_placeholder_hint),
                passwordTrailingButton = PasswordTrailingButton(
                    showButtonText = showText,
                    hideButtonText = hideText
                )
            )
        }

        ExamplesSlot {
            HmrcCardView {
                PasswordInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    value = example1Value,
                    onInputValueChange = {
                        viewModel.isEmptyValidation(it, error1Text, 0)
                        example1Value = it
                    },
                    labelText = stringResource(id = R.string.password_input_example_1_label),
                    hintText = stringResource(id = R.string.password_input_example_1_hint),
                    errorText = viewModel.textInputErrorEmptyValidation.collectAsStateWithLifecycle().value,
                    errorContentDescription = stringResource(R.string.password_input_example_1_error_content_description),
                    numericOnly = true,
                    maxChars = 4,
                    passwordTrailingButton = PasswordTrailingButton(
                        showButtonText = stringResource(id = R.string.password_input_example_1_show_text),
                        hideButtonText = stringResource(id = R.string.password_input_example_1_hide_text),
                        showButtonContentDescription = stringResource(id = R.string.password_input_example_1_show_content_description),
                        showButtonStateDescription = stringResource(id = R.string.password_input_example_1_show_state_description),
                        hideButtonContentDescription = stringResource(id = R.string.password_input_example_1_hide_content_description),
                        hideButtonStateDescription = stringResource(id = R.string.password_input_example_1_hide_state_description),
                    )
                )

                PasswordInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    value = example2Value,
                    onInputValueChange = {
                        viewModel.isEmptyValidation(it, errorText, 1)
                        example2Value = it
                    },
                    labelText = stringResource(id = R.string.password_input_example_2_label),
                    hintText = stringResource(id = R.string.password_input_example_2_hint),
                    errorText = viewModel.textInputErrorEmptyValidation1.collectAsStateWithLifecycle().value,
                    passwordTrailingButton = PasswordTrailingButton(
                        showButtonText = showText,
                        hideButtonText = hideText,
                    ),
                    numericOnly = false
                )

                PasswordInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    value = example3Value,
                    onInputValueChange = {
                        viewModel.isEmptyValidation(it, errorText, 2)
                        example3Value = it
                    },
                    labelText = stringResource(id = R.string.password_input_example_3_label),
                    hintText = stringResource(id = R.string.password_input_example_3_hint),
                    errorText = viewModel.textInputErrorEmptyValidation2.collectAsStateWithLifecycle().value,
                    passwordTrailingButton = PasswordTrailingButton(
                        showButtonText = showText,
                        hideButtonText = hideText
                    ),
                    requiredSequencesSpacing = false,
                    numericOnly = false
                )
            }
        }
    }
}
