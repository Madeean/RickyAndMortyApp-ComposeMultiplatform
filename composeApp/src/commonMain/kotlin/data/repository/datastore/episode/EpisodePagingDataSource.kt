package data.repository.datastore.episode

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import data.network.ApiService
import data.repository.network.episode.model.EpisodeDetailModelDataResponse
import data.repository.network.episode.model.EpisodeModelDataResponse
import domain.episode.model.network.EpisodeDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import util.RequestState


class EpisodePagingDataSource(
  private val httpClient: HttpClient,
  private val apiService: ApiService,
  private val name: String,
): PagingSource<Int, EpisodeDetailModelDomain>() {
  override fun getRefreshKey(state: PagingState<Int, EpisodeDetailModelDomain>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeDetailModelDomain> {
    val position = params.key ?: 1
    return try {

      val response = httpClient.get("${apiService.BASE_URL_EPISODE}?name=$name&page=$position")

        val apiResponse = response.body<EpisodeModelDataResponse>()
        val data = EpisodeDetailModelDataResponse.transforms(apiResponse.results)

        toLoadResult(data = data, nextKey = if(data.isEmpty()) null else position +1)

    }catch (e:Exception){
      LoadResult.Error(e)
    }
  }

  private fun toLoadResult(
    data: List<EpisodeDetailModelDomain>, prevKey: Int? = null, nextKey: Int? = null
  ): LoadResult<Int, EpisodeDetailModelDomain> {
    return LoadResult.Page(
      data = data, prevKey = prevKey, nextKey = nextKey
    )
  }
}