package uk.gov.hmrc.sample_compose_fragments.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItems
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _items = MutableStateFlow(listOf<ColorItems>())
    val items: StateFlow<List<ColorItems>> get() = _items

    init {
        getFakeData()
    }

    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _items.value = repository.getLists()
            }
        }
    }
}