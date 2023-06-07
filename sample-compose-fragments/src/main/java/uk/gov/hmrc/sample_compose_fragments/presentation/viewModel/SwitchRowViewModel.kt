package uk.gov.hmrc.sample_compose_fragments.presentation.viewModel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.gov.hmrc.sample_compose_components.R
import javax.inject.Inject

@HiltViewModel
class SwitchRowViewModel @Inject constructor(): ViewModel() {

    private val _placeholderSwitchUiState = MutableStateFlow(createPlaceholderSwitchUiState())
    val placeholderSwitchUiState: StateFlow<SwitchUiState> get() = _placeholderSwitchUiState

    private val _examplesUiState = MutableStateFlow(createExamplesSwitchUiState())
    val examplesUiState: StateFlow<ExamplesUiState> get() = _examplesUiState

    private val _showToast = MutableStateFlow<Int?>(null)
    val showToast: StateFlow<Int?> get() = _showToast

    fun onPlaceholderSwitchChanged(enabled: Boolean) {
        val body = if (enabled) R.string.switch_row_placeholder_body_on else R.string.switch_row_placeholder_body_off
        _placeholderSwitchUiState.value = _placeholderSwitchUiState.value.copy(body = body, enabled = enabled)
        showToast(body)
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
                SwitchRowExample.THREE -> {
                    showToast(
                        if (enabled) {
                            R.string.switch_row_example_3_enabled_toast
                        } else R.string.switch_row_example_3_disabled_toast
                    )
                    copy(exampleThree = exampleThree.copy(enabled = enabled))
                }
                SwitchRowExample.FOUR -> {
                    showToast(
                        if (enabled) {
                            R.string.switch_row_example_4_enabled_toast
                        } else R.string.switch_row_example_4_disabled_toast
                    )
                    copy(exampleFour = exampleFour.copy(enabled = enabled))
                }
                SwitchRowExample.FIVE -> {
                    showToast(
                        if (enabled) {
                            R.string.switch_row_example_5_enabled_toast
                        } else R.string.switch_row_example_5_disabled_toast
                    )
                    copy(exampleFive = exampleFive.copy(enabled = enabled))
                }
            }
        }
    }

    private fun showToast(@StringRes text: Int) {
        _showToast.value = text
    }

    private fun createPlaceholderSwitchUiState() = SwitchUiState(
        title = R.string.switch_row_placeholder_title,
        body = R.string.switch_row_placeholder_body_off,
        enabled = false,
    )

    private fun createExamplesSwitchUiState() = ExamplesUiState(
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
        ),
        exampleThree = SwitchUiState(
            title = null,
            body = R.string.switch_row_example_3_title,
            enabled = false,
        ),
        exampleFour = SwitchUiState(
            title = R.string.switch_row_example_4_title,
            body = null,
            enabled = false,
        ),
        exampleFive = SwitchUiState(
            title = R.string.switch_row_example_5_title,
            body = R.string.switch_row_example_5_body,
            enabled = false,
        ),
    )

    data class ExamplesUiState(
        val exampleOne: SwitchUiState,
        val exampleTwo: SwitchUiState,
        val exampleThree: SwitchUiState,
        val exampleFour: SwitchUiState,
        val exampleFive: SwitchUiState,
    )

    data class SwitchUiState(
        @StringRes val title: Int?,
        @StringRes val body: Int?,
        val enabled: Boolean = false,
        @StringRes val enabledContentDesc: Int? = null,
    )

    enum class SwitchRowExample {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
