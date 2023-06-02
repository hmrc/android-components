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

    private val _placeholderBodyText = MutableStateFlow(R.string.switch_row_placeholder_body_off)
    val placeholderBodyText: StateFlow<Int> get() = _placeholderBodyText

    private val _showToast = MutableStateFlow<Int?>(null)
    val showToast: StateFlow<Int?> get() = _showToast

    fun onPlaceholderSwitchChanged(enabled: Boolean) {
        _placeholderBodyText.value = if (enabled) {
            R.string.switch_row_placeholder_body_on
        } else R.string.switch_row_placeholder_body_off
        showToast(_placeholderBodyText.value)
    }

    fun onSwitchChanged(enabled: Boolean) {
        showToast(if (enabled) R.string.switch_row_enabled else R.string.switch_row_disabled)
    }

    private fun showToast(@StringRes text: Int) {
        _showToast.value = text
    }
}
