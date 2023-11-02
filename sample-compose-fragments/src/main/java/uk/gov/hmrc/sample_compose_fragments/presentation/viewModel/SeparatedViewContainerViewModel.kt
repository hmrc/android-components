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
package uk.gov.hmrc.sample_compose_fragments.presentation.viewModel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.gov.hmrc.sample_compose_components.R
import javax.inject.Inject

@HiltViewModel
class SeparatedViewContainerViewModel @Inject constructor() : ViewModel() {


    private val _examplesUiState = MutableStateFlow(createInitialExamplesSwitchUiState())
    val examplesUiState: StateFlow<ExamplesUiState> get() = _examplesUiState

    private val _showToast = MutableStateFlow<Int?>(null)
    val showToast: StateFlow<Int?> get() = _showToast

    private fun createInitialExamplesSwitchUiState() = ExamplesUiState(
        exampleOne = SwitchUiState(
            title = R.string.switch_row_example_1_title,
            body = R.string.switch_row_example_1_body,
            enabled = false,
            enabledContentDesc = R.string.switch_row_example_1_disabled_content_desc,
        ),
        exampleTwo = SwitchUiState(
            title = R.string.switch_row_example_2_title,
            body = R.string.switch_row_example_2_body,
            enabled = true,
        )
    )

    data class ExamplesUiState(
        val exampleOne: SwitchUiState,
        val exampleTwo: SwitchUiState,
    )

    private fun showToast(@StringRes text: Int) {
        _showToast.value = text
    }

    fun onExampleSwitchChanged(enabled: Boolean, switchRowExample: SwitchRowExample) {
        _examplesUiState.value = with(_examplesUiState.value) {
            when (switchRowExample) {
                SwitchRowExample.ONE -> {
                    val (contentDesc, toggledToastText) = if (enabled) {
                        Pair(
                            R.string.switch_row_example_1_enabled_content_desc,
                            R.string.switch_row_example_1_enabled_toast
                        )
                    } else {
                        Pair(
                            R.string.switch_row_example_1_disabled_content_desc,
                            R.string.switch_row_example_1_disabled_toast
                        )
                    }
                    showToast(toggledToastText)
                    copy(exampleOne = exampleOne.copy(enabled = enabled, enabledContentDesc = contentDesc))
                }

                SwitchRowExample.TWO -> {
                    showToast(
                        if (enabled) {
                            R.string.switch_row_example_2_enabled_toast
                        } else R.string.switch_row_example_2_disabled_toast
                    )
                    copy(exampleTwo = exampleTwo.copy(enabled = enabled))
                }
            }
        }
    }

    data class SwitchUiState(
        @StringRes val title: Int?,
        @StringRes val body: Int?,
        val enabled: Boolean = false,
        @StringRes val enabledContentDesc: Int? = null,
    )

    enum class SwitchRowExample {
        ONE, TWO
    }

}
