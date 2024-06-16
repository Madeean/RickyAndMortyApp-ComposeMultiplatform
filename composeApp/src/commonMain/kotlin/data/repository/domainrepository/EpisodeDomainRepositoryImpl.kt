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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import util.RequestState

class EpisodeDomainRepositoryImpl(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) : EpisodeDomainRepository {
//  override fun getAllEpisode(name: String, viewmodelScope: CoroutineScope): Flow<RequestState<List<EpisodeDetailModelDomain>>> {
//    return flow {
//      emit(RequestState.Loading)
//      println("MASUK 2")
//      val dataStore = EpisodeDataStore(httpClient, apiService)
//      val data = dataStore.getAllEpisode()
//      emit(data)
//    }.flowOn(Dispatchers.IO)
//  }

  override fun getEpisodePaging(scope: CoroutineScope,name: String): Flow<PagingData<EpisodeDetailModelDomain>> = Pager(
    config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = false),
    pagingSourceFactory = {
        EpisodePagingDataSource(httpClient,apiService,name)
    }
  ).flow.cachedIn(scope)

}