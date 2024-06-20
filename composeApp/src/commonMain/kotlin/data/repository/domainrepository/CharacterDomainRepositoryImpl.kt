package data.repository.domainrepository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import data.network.ApiService
import data.repository.datastore.character.CharacterDataSource
import data.repository.datastore.character.CharacterPagingDataSource
import domain.character.CharacterDomainRepository
import domain.character.model.network.CharacterDetailModelDomain
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.RequestState
import util.RequestState.Loading

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

  override fun getListCharacter(characterId: String): Flow<RequestState<List<CharacterDetailModelDomain>>> {
    return flow{
      emit(Loading)
      val dataSource = CharacterDataSource(httpClient,apiService)
      val data = dataSource.getListCharacter(characterId)
      emit(data)
    }
  }
}