package data.repository.domainrepository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import data.network.ApiService
import data.repository.datastore.character.CharacterPagingDataSource
import data.repository.datastore.episode.EpisodePagingDataSource
import domain.character.CharacterDomainRepository
import domain.character.model.network.CharacterDetailModelDomain
import domain.episode.EpisodeDomainRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class CharacterDomainRepositoryImpl(
  private val httpClient: HttpClient,
  private val apiService: ApiService
): CharacterDomainRepository {
  override fun getCharacterPaging(
    scope: CoroutineScope,
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String
  ): Flow<PagingData<CharacterDetailModelDomain>> = Pager(
  config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = false),
  pagingSourceFactory = {
    CharacterPagingDataSource(httpClient,apiService,name,status,species,type,gender)
  }
  ).flow.cachedIn(scope)
}