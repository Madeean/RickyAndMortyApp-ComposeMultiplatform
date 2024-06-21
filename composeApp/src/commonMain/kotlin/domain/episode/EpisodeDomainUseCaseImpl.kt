package domain.episode

import app.cash.paging.PagingData
import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

class EpisodeDomainUseCaseImpl(
  private val repository: EpisodeDomainRepository
) : EpisodeDomainUseCase {
  override fun getEpisodePaging(
    scope: CoroutineScope,
    name: String
  ): Flow<PagingData<EpisodeDetailModelDomain>> {
    return repository.getEpisodePaging(scope, name)
  }

  override fun getDetailEpisode(
    scope: CoroutineScope,
    idEpisode: Int
  ): Flow<RequestState<EpisodeDetailModelDomain>> {
    return repository.getDetailEpisode(scope, idEpisode)
  }

  override fun getEpisodeFromUrl(url: String): Flow<RequestState<List<EpisodeDetailModelDomain>>> {
    return repository.getEpisodeFromUrl(url)
  }

}