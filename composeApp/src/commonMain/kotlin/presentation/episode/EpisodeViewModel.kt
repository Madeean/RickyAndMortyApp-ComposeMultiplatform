package presentation.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.episode.EpisodeDomainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class EpisodeViewModel(private val useCase: EpisodeDomainUseCase) : ViewModel() {
  private var _message: MutableStateFlow<String> = MutableStateFlow("")
  val message: StateFlow<String> = _message


  fun fetchData() {
    viewModelScope.launch {
      useCase.getMessage().collect { data ->
        _message.value = data
      }
    }
  }
}