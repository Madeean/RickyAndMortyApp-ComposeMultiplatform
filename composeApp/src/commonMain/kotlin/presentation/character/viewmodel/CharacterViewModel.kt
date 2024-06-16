package presentation.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import domain.character.CharacterDomainUseCase
import domain.character.model.network.CharacterDetailModelDomain
import domain.episode.EpisodeDomainUseCase
import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CharacterViewModel(private val useCase: CharacterDomainUseCase) : ViewModel() {
  private var _character: MutableStateFlow<PagingData<CharacterDetailModelDomain>> =
    MutableStateFlow(PagingData.empty())
  val character: StateFlow<PagingData<CharacterDetailModelDomain>> = _character


  fun getCharacterViewModel(
    name: String = "",
    status: String = "",
    species: String = "",
    type: String = "",
    gender: String = ""
  ) {
    viewModelScope.launch {
      useCase.getEpisodePaging(viewModelScope, name, status, species, type, gender).collectLatest {
        _character.value = it
      }
    }
  }
}