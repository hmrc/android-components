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
        showToast(with(_placeholderSwitchUiState.value) { if (enabled) enabledToastText else disabledToastText })
    }

    fun onExampleSwitchChanged(enabled: Boolean, switchRowExample: SwitchRowExample) {
        _examplesUiState.value = with(_examplesUiState.value) {
            when (switchRowExample) {
                SwitchRowExample.ONE -> {
                    showToast(if (enabled) exampleOne.enabledToastText else exampleOne.disabledToastText)
                    copy(exampleOne = exampleOne.copy(enabled = enabled))
                }
                SwitchRowExample.TWO -> {
                    showToast(if (enabled) exampleTwo.enabledToastText else exampleTwo.disabledToastText)
                    copy(exampleTwo = exampleTwo.copy(enabled = enabled))
                }
                SwitchRowExample.THREE -> {
                    showToast(if (enabled) exampleThree.enabledToastText else exampleThree.disabledToastText)
                    copy(exampleThree = exampleThree.copy(enabled = enabled))
                }
                SwitchRowExample.FOUR -> {
                    showToast(if (enabled) exampleFour.enabledToastText else exampleFour.disabledToastText)
                    copy(exampleFour = exampleFour.copy(enabled = enabled))
                }
                SwitchRowExample.FIVE -> {
                    showToast(if (enabled) exampleFive.enabledToastText else exampleFive.disabledToastText)
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
        enabledToastText = R.string.switch_row_enabled,
        disabledToastText = R.string.switch_row_placeholder_body_off
    )

    private fun createExamplesSwitchUiState() = ExamplesUiState(
        exampleOne = SwitchUiState(
            title = R.string.switch_row_example_1_title,
            body = R.string.switch_row_example_1_body,
            enabled = false,
            enabledToastText = R.string.switch_row_example_1_enabled_content_desc,
            disabledToastText = R.string.switch_row_example_1_disabled_content_desc,
        ),
        exampleTwo = SwitchUiState(
            title = R.string.switch_row_example_2_title,
            body = R.string.switch_row_example_2_body,
            enabled = true,
            enabledToastText = R.string.switch_row_example_2_enabled_content_desc,
            disabledToastText = R.string.switch_row_example_2_disabled_content_desc,
        ),
        exampleThree = SwitchUiState(
            title = null,
            body = R.string.switch_row_example_3_title,
            enabled = false,
            enabledToastText = R.string.switch_row_example_3_enabled_content_desc,
            disabledToastText = R.string.switch_row_example_3_disabled_content_desc,
        ),
        exampleFour = SwitchUiState(
            title = R.string.switch_row_example_4_title,
            body = null,
            enabled = false,
            enabledToastText = R.string.switch_row_example_4_enabled_content_desc,
            disabledToastText = R.string.switch_row_example_4_disabled_content_desc,
        ),
        exampleFive = SwitchUiState(
            title = R.string.switch_row_example_5_title,
            body = R.string.switch_row_example_5_body,
            enabled = false,
            enabledToastText = R.string.switch_row_example_5_enabled_content_desc,
            disabledToastText = R.string.switch_row_example_5_disabled_content_desc,
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
        @StringRes val enabledToastText: Int,
        @StringRes val disabledToastText: Int,
    )

    enum class SwitchRowExample {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
