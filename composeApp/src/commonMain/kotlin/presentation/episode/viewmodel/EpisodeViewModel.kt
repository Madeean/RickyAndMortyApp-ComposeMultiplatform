package presentation.episode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import domain.episode.EpisodeDomainUseCase
import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import util.RequestState


class EpisodeViewModel(private val useCase: EpisodeDomainUseCase) : ViewModel() {

  private var _episode: MutableStateFlow<PagingData<EpisodeDetailModelDomain>> =
    MutableStateFlow(PagingData.empty())
  val episode: StateFlow<PagingData<EpisodeDetailModelDomain>> = _episode

  private val _episodeDetail: MutableStateFlow<RequestState<EpisodeDetailModelDomain>> =
    MutableStateFlow(RequestState.Idle)
  val episodeDetail: StateFlow<RequestState<EpisodeDetailModelDomain>> = _episodeDetail

  private val _listEpisodeFromUrl: MutableStateFlow<RequestState<List<EpisodeDetailModelDomain>>> =
    MutableStateFlow(RequestState.Idle)
  val listEpisodeFromUrl: StateFlow<RequestState<List<EpisodeDetailModelDomain>>> = _listEpisodeFromUrl

  fun getEpisodePaging(name: String = "") {
    viewModelScope.launch {
      useCase.getEpisodePaging(viewModelScope, name).collectLatest { it ->
        _episode.value = it
      }
    }
  }

  fun getDetailEpisode(episodeId: Int) {
    viewModelScope.launch {
      useCase.getDetailEpisode(viewModelScope, episodeId).collect {
        _episodeDetail.value = it
      }
    }
  }

  fun getListEpisodeFromUrl(url: String){
    viewModelScope.launch {
      useCase.getEpisodeFromUrl(url).collect{
        _listEpisodeFromUrl.value = it
      }
    }
  }
}