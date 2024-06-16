package data.repository.datastore.character

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import data.network.ApiService
import data.repository.network.character.model.CharacterDetailModelDataResponse
import data.repository.network.character.model.CharacterModelDataResponse
import domain.character.model.network.CharacterDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay

class CharacterPagingDataSource(
  private val httpClient: HttpClient,
  private val apiService: ApiService,
  private val name: String,
  private val status: String,
  private val species: String,
  private val type: String,
  private val gender: String
) : PagingSource<Int, CharacterDetailModelDomain>() {
  override fun getRefreshKey(state: PagingState<Int, CharacterDetailModelDomain>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetailModelDomain> {
    val position = params.key ?: 1
    return try {
      val response =
        httpClient.get("${apiService.BASE_URL_CHARACTER}?name=$name&status=$status&species=$species&type=$type&gender=$gender&page=$position")
      if (response.status.value == 200) {
        val apiResponse = response.body<CharacterModelDataResponse>()
        val data = CharacterDetailModelDataResponse.transforms(apiResponse.results)

        toLoadResult(data = data, nextKey = if (data.isEmpty()) null else position + 1)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        LoadResult.Error(error)
      }
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  private fun toLoadResult(
    data: List<CharacterDetailModelDomain>, prevKey: Int? = null, nextKey: Int? = null
  ): LoadResult<Int, CharacterDetailModelDomain> {
    return LoadResult.Page(
      data = data, prevKey = prevKey, nextKey = nextKey
    )
  }
}