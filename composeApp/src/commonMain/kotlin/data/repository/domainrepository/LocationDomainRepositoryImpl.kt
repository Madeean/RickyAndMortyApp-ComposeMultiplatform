package data.repository.domainrepository

import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingData
import data.network.ApiService
import data.repository.datastore.location.LocationPagingDataSource
import domain.location.LocationDomainRepository
import domain.location.model.network.LocationDetailModelDomain
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LocationDomainRepositoryImpl(
  private val httpClient: HttpClient,
  private val apiService: ApiService
):LocationDomainRepository {
  override fun getLocationPaging(
    scope: CoroutineScope,
    name: String,
    type: String,
    dimension: String
  ): Flow<PagingData<LocationDetailModelDomain>> = Pager(
    config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = false),
    pagingSourceFactory = {
      LocationPagingDataSource(httpClient,apiService,name, type, dimension)
    }
  ).flow.cachedIn(scope)
}