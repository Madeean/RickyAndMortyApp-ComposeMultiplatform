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


class EpisodeViewModel(private val useCase: EpisodeDomainUseCase) : ViewModel() {

  private var _episode: MutableStateFlow<PagingData<EpisodeDetailModelDomain>> =
    MutableStateFlow(PagingData.empty())
  val episode: StateFlow<PagingData<EpisodeDetailModelDomain>> = _episode

  fun getEpisodePaging(name:String = ""){
    viewModelScope.launch {
      useCase.getEpisodePaging(viewModelScope, name).collectLatest { it ->
        _episode.value = it
      }
    }
  }
}