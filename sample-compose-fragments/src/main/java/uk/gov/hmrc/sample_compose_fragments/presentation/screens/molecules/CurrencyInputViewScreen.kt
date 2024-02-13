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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.gov.hmrc.components.compose.molecule.input.CurrencyInputView
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.CurrencyInputViewModel

@Composable
fun CurrencyInputViewScreen() {

    val viewModel = viewModel<CurrencyInputViewModel>()

    val errorText = stringResource(id = R.string.currency_input_example_error)

    ScreenScrollViewColumn {
        PlaceholderSlot {
            CurrencyInputView(
                labelText = stringResource(id = R.string.currency_input_placeholder_label),
                hintText = stringResource(id = R.string.currency_input_placeholder_hint),
                placeholderText = stringResource(id = R.string.currency_input_placeholder_placeholder),
                enableDecimal = true,
            )
        }

        ExamplesSlot {
            HmrcCardView {
                CurrencyInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText, 0) },
                    errorText = viewModel.textInputErrorEmptyValidation.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_1_label),
                    hintText = stringResource(id = R.string.currency_input_example_1_hint),
                    enableDecimal = true
                )

                CurrencyInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText, 1) },
                    errorText = viewModel.textInputErrorEmptyValidation1.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_2_label),
                    hintText = stringResource(id = R.string.currency_input_example_2_hint),
                    enableDecimal = false
                )

                CurrencyInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    initialInputValue = stringResource(id = R.string.currency_input_example_3_text),
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText, 2) },
                    errorText = viewModel.textInputErrorEmptyValidation2.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_3_label),
                    hintText = stringResource(id = R.string.currency_input_example_3_hint),
                    enableDecimal = true
                )

                CurrencyInputView(
                    modifier = Modifier.padding(
                        horizontal = HmrcTheme.dimensions.hmrcSpacing16,
                        vertical = HmrcTheme.dimensions.hmrcSpacing24,
                    ),
                    initialInputValue = stringResource(id = R.string.currency_input_example_3_text),
                    onInputValueChange = { viewModel.isEmptyValidation(it, errorText, 3) },
                    errorText = viewModel.textInputErrorEmptyValidation3.collectAsStateWithLifecycle().value,
                    labelText = stringResource(id = R.string.currency_input_example_4_label),
                    hintText = stringResource(id = R.string.currency_input_example_4_hint),
                    enableDecimal = true
                )
            }
        }
    }
}
