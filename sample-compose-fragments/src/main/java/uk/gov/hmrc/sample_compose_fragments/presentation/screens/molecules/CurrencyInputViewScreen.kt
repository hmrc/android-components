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

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.gov.hmrc.components.compose.molecule.input.CurrencyInputView
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcCard
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.CurrencyInputViewModel

@Composable
fun CurrencyInputViewScreen() {

    val viewModel = viewModel<CurrencyInputViewModel>()

    val errorText1 = stringResource(id = R.string.currency_input_example_1_error)
    val errorText2 = stringResource(id = R.string.currency_input_example_2_error)
    val errorText3 = stringResource(id = R.string.currency_input_example_3_error)

    ScreenScrollViewColumn {
        PlaceholderSlot {
            CurrencyInputView(
                labelText = stringResource(id = R.string.currency_input_placeholder_hint),
                enableDecimal = true,
            )
        }

        ExamplesSlot {
            HmrcCard() {
                CurrencyInputView(
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText1, 0) },
                    errorText = viewModel.textInputErrorEmptyValidation.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_1_hint),
                    enableDecimal = true
                    )

                CurrencyInputView(
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText2, 1) },
                    errorText = viewModel.textInputErrorEmptyValidation1.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_2_hint),
                    enableDecimal = false
                    )

                CurrencyInputView(
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText3, 2) },
                    errorText = viewModel.textInputErrorEmptyValidation2.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_3_hint),
                    placeholderText = stringResource(id = R.string.currency_input_example_3_text),
                    enableDecimal = true
                    )
            }
        }
    }
}