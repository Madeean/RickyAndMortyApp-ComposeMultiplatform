package presentation.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import domain.character.CharacterDomainUseCase
import domain.character.model.network.CharacterDetailModelDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import util.RequestState


class CharacterViewModel(private val useCase: CharacterDomainUseCase) : ViewModel() {
  private var _character: MutableStateFlow<PagingData<CharacterDetailModelDomain>> =
    MutableStateFlow(PagingData.empty())
  val character: StateFlow<PagingData<CharacterDetailModelDomain>> = _character

  private var _listCharacter: MutableStateFlow<RequestState<List<CharacterDetailModelDomain>>> =
    MutableStateFlow(RequestState.Idle)
  val listCharacter: StateFlow<RequestState<List<CharacterDetailModelDomain>>> = _listCharacter

  private var _detailCharacter: MutableStateFlow<RequestState<CharacterDetailModelDomain>> = MutableStateFlow(RequestState.Idle)
  val detailCharacter: StateFlow<RequestState<CharacterDetailModelDomain>> = _detailCharacter

  fun getCharacterPaging(
    name: String = "",
    status: String = "",
    species: String = "",
    type: String = "",
    gender: String = ""
  ) {
    viewModelScope.launch {
      useCase.getCharacterPaging(viewModelScope, name, status, species, type, gender).collectLatest {
        _character.value = it
      }
    }
  }

  fun getListCharacter(
    characterList:String
  ){
    viewModelScope.launch {
      useCase.getListCharacter(characterList).collect{
        _listCharacter.value = it
      }
    }
  }

  fun getDetailCharacter(
    characterId: Int
  ){
    viewModelScope.launch {
      useCase.getDetailCharacter(characterId).collect{
        _detailCharacter.value = it
      }
    }
  }

}