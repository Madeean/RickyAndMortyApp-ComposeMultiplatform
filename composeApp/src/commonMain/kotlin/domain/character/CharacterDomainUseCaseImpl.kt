package domain.character

import app.cash.paging.PagingData
import domain.character.model.network.CharacterDetailModelDomain
import domain.episode.EpisodeDomainRepository
import domain.episode.EpisodeDomainUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class CharacterDomainUseCaseImpl(
  private val repository: CharacterDomainRepository
): CharacterDomainUseCase {
  override fun getEpisodePaging(
    scope: CoroutineScope,
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String
  ): Flow<PagingData<CharacterDetailModelDomain>> {
    return repository.getCharacterPaging(scope,name,status,species,type,gender)
  }
}