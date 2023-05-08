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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.gov.hmrc.components.compose.molecule.input.TextInputView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.TextInputViewModel

@Composable
fun TextInputViewScreen() {

    val viewModel = viewModel<TextInputViewModel>()

    val errorTextEx1 = stringResource(id = R.string.text_input_this_is_error)
    val errorTextEx2 = stringResource(id = R.string.text_input_example_2_error)
    val errorTextEx3 = stringResource(id = R.string.text_input_example_3_error)


    ScreenScrollViewColumn {
        val characterCount = 50
        PlaceholderSlot {
            TextInputView(
                onInputValueChange = { viewModel.validateCharCount(characterCount, it, null, 0) },
                errorText = viewModel.textInputErrorCharCount.collectAsStateWithLifecycle().value,
                labelText = stringResource(id = R.string.text_input_placeholder_hint),
                characterCount = characterCount,
            )
        }

        ExamplesSlot {
            val characterCount1 = 5
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                TextInputView(
                    onInputValueChange = { viewModel.validateCharCount(characterCount1, it, errorTextEx1, 1) },
                    errorText = viewModel.textInputErrorCharCount1.collectAsStateWithLifecycle().value,
                    labelText = stringResource(R.string.text_input_example_1_hint),
                    characterCount = characterCount1,
                )

                TextInputView(
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorTextEx2, 0) },
                    errorText = viewModel.textInputError.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.text_input_example_2_hint)
                )

                TextInputView(
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorTextEx3, 1) },
                    errorText = viewModel.textInputErrorEmptyValidation.collectAsStateWithLifecycle().value,
                    labelText = stringResource(R.string.text_input_example_3_hint),
                    singleLine = true
                )
            }
        }
    }
}
