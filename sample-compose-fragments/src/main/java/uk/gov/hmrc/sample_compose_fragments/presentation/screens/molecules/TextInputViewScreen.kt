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
import uk.gov.hmrc.components.compose.molecule.input.TextInputView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.ptcalc.common.compose.core.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.TextInputViewModel

@Composable
fun TextInputViewScreen() {

    val viewModel = viewModel<TextInputViewModel>()

    val errorTextEx1 = stringResource(id = R.string.text_input_this_is_error)
    val errorTextEx2 = stringResource(id = R.string.text_input_example_2_error)
    val errorTextEx3 = stringResource(id = R.string.text_input_example_3_error)

    var placeholderValue: String by rememberSaveable { mutableStateOf("") }
    var example1Value: String by rememberSaveable { mutableStateOf("") }
    var example2Value: String by rememberSaveable { mutableStateOf("") }
    var example3Value: String by rememberSaveable { mutableStateOf("") }


    ScreenScrollViewColumn {
        val characterCount = 50
        PlaceholderSlot {
            TextInputView(
                value = placeholderValue,
                onInputValueChange = {
                    viewModel.validateCharCount(
                        characterCount = characterCount,
                        input = it,
                        errorText = null,
                        id = 0
                    )
                    placeholderValue = it
                },
                errorText = viewModel.textInputErrorCharCount.collectAsStateWithLifecycle().value,
                labelText = stringResource(id = R.string.text_input_placeholder_label),
                hintText = stringResource(id = R.string.text_input_placeholder_hint),
                placeholderText = stringResource(id = R.string.text_input_placeholder_placeholder),
                characterCount = characterCount,
            )
        }

        ExamplesSlot {
            HmrcCardView {
                TextInputView(
                    value = example1Value,
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    onInputValueChange = {
                        viewModel.validateCharCount(5, it, errorTextEx1, 1)
                        example1Value = it
                    },
                    errorText = viewModel.textInputErrorCharCount1.collectAsStateWithLifecycle().value,
                    labelText = stringResource(R.string.text_input_example_1_hint),
                    labelContentDescription = stringResource(R.string.text_input_example_1_content_description),
                    characterCount = 5,
                    maxChars = 5
                )

                TextInputView(
                    value = example2Value,
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    onInputValueChange = {
                        viewModel.isEmptyValidation(it, errorTextEx2, 0)
                        example2Value = it
                    },
                    errorText = viewModel.textInputError.collectAsStateWithLifecycle().value,
                    hintText = stringResource(id = R.string.text_input_example_2_hint)
                )

                TextInputView(
                    value = example3Value,
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    onInputValueChange = {
                        viewModel.isEmptyValidation(it, errorTextEx3, 1)
                        example3Value = it
                    },
                    errorText = viewModel.textInputErrorEmptyValidation.collectAsStateWithLifecycle().value,
                    labelText = stringResource(R.string.text_input_example_3_hint),
                    singleLine = true
                )
            }
        }
    }
}

@HmrcAllDevicePreview
@Composable
internal fun TextInputViewScreenPreview() {
    HmrcTheme {
        HmrcSurface {
            TextInputViewScreen()
        }
    }
}

