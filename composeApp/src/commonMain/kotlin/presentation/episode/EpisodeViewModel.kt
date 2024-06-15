package presentation.episode

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.episode.EpisodeDomainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(private val useCase: EpisodeDomainUseCase): ScreenModel {
  private var _message:MutableStateFlow<String> = MutableStateFlow("")
  val message: StateFlow<String> = _message

  init {
    println("viewmodel Assign")
  }

  fun fetchData() {
    println("MADE REIHAN masuk1")

    screenModelScope.launch {
      useCase.getMessage().collect { data ->
        _message.value = data
        println("MADE REIHAN $data")
      }
    }
  }
}