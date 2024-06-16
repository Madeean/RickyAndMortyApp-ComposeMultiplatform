package domain.character

import app.cash.paging.PagingData
import domain.character.model.network.CharacterDetailModelDomain
import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CharacterDomainUseCase {
  fun getEpisodePaging(
    scope: CoroutineScope,
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String
  ): Flow<PagingData<CharacterDetailModelDomain>>
}