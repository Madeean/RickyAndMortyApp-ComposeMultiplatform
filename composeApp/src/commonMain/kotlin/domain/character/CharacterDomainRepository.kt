package domain.character

import app.cash.paging.PagingData
import domain.character.model.network.CharacterDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

interface CharacterDomainRepository {
  fun getCharacterPaging(
    scope: CoroutineScope,
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String
  ): Flow<PagingData<CharacterDetailModelDomain>>

  fun getListCharacter(
    characterId: String
  ): Flow<RequestState<List<CharacterDetailModelDomain>>>
}