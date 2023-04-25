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
import uk.gov.hmrc.components.compose.molecule.input.TextInputView
import uk.gov.hmrc.components.compose.molecule.input.TextInputViewModel
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.PlaceholderSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TextInputViewScreen(
    viewModel: TextInputViewModel = viewModel(),
    fragmentsViewModel: uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.TextInputViewModel = viewModel()
) {

    ScreenScrollViewColumn {
        PlaceholderSlot {
            TextInputView(
                errorText = "Error: Validation error",
                isError = {fragmentsViewModel.textMatchesValidation("error", viewModel.value)},
                labelText = "This is a label. Type 'error' for error validation",
                supportingText = "This is supporting text",
                vm = viewModel
            )
        }

        ExamplesSlot {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0),
                colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
            ) {
                TextInputView(
                    errorText = "Error: Must not be empty",
                    isError = {fragmentsViewModel.isEmptyValidation(viewModel.value)},
                    labelText = "This is a label",
                    supportingText = "This is supporting text",
                    vm = viewModel
                )

                val characterCount = 20

                TextInputView(
                    errorText = "ERROR",
                    isError = {fragmentsViewModel.validateCharCount(characterCount, viewModel.value)},
                    labelText = "Enter username",
                    supportingText = "Username",
                    singleLine = true,
                    characterCount = characterCount,
                    counterEnabled = true,
                    vm = viewModel
                )
            }
        }
    }
}
