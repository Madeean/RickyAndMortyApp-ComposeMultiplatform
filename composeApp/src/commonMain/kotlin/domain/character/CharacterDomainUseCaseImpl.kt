package domain.character

import app.cash.paging.PagingData
import domain.character.model.network.CharacterDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

class CharacterDomainUseCaseImpl(
  private val repository: CharacterDomainRepository
): CharacterDomainUseCase {
  override fun getCharacterPaging(
    scope: CoroutineScope,
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String
  ): Flow<PagingData<CharacterDetailModelDomain>> {
    return repository.getCharacterPaging(scope,name,status,species,type,gender)
  }

  override fun getListCharacter(characterId: String): Flow<RequestState<List<CharacterDetailModelDomain>>> {
    return repository.getListCharacter(characterId)
  }

  override fun getDetailCharacter(characterId: Int): Flow<RequestState<CharacterDetailModelDomain>> {
    return repository.getDetailCharacter(characterId)
  }
}