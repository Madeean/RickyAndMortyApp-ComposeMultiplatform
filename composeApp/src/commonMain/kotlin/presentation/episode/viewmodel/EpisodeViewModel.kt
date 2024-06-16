package presentation.episode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.episode.EpisodeDomainUseCase
import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import util.RequestState


class EpisodeViewModel(private val useCase: EpisodeDomainUseCase) : ViewModel() {
  private var _episode: MutableStateFlow<RequestState<List<EpisodeDetailModelDomain>>> =
    MutableStateFlow(RequestState.Idle)
  val episode:StateFlow<RequestState<List<EpisodeDetailModelDomain>>> = _episode


  fun getAllData() {
    println("MASUK 1")
    viewModelScope.launch {
      useCase.getAllEpisode("",viewModelScope).collect{
        _episode.value = it
      }
    }
  }
}