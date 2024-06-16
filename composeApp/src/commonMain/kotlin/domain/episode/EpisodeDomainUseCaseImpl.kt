package domain.episode

import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

class EpisodeDomainUseCaseImpl(
  private val repository: EpisodeDomainRepository
):EpisodeDomainUseCase {
  override fun getAllEpisode(name: String,viewmodelScope: CoroutineScope): Flow<RequestState<List<EpisodeDetailModelDomain>>> {
   return repository.getAllEpisode(name,viewmodelScope)
  }

  override suspend fun getMessage(): Flow<String> {
    return repository.getMessage()
  }

}