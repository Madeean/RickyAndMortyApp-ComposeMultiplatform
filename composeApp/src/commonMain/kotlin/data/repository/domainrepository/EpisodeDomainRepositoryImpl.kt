package data.repository.domainrepository

import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingData
import data.network.ApiService
import data.repository.datastore.episode.EpisodeDataStore
import data.repository.datastore.episode.EpisodePagingDataSource
import domain.episode.EpisodeDomainRepository
import domain.episode.model.network.EpisodeDetailModelDomain
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.RequestState

class EpisodeDomainRepositoryImpl(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) : EpisodeDomainRepository {

  override fun getEpisodePaging(
    scope: CoroutineScope,
    name: String
  ): Flow<PagingData<EpisodeDetailModelDomain>> = Pager(
    config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = false),
    pagingSourceFactory = {
      EpisodePagingDataSource(httpClient, apiService, name)
    }
  ).flow.cachedIn(scope)

  override fun getDetailEpisode(
    scope: CoroutineScope,
    idEpisode: Int
  ): Flow<RequestState<EpisodeDetailModelDomain>> {
    return flow {
      emit(RequestState.Loading)
      val dataSource = EpisodeDataStore(httpClient, apiService)
      val data = dataSource.getDetailEpisode(idEpisode)
      emit(data)
    }
  }

}