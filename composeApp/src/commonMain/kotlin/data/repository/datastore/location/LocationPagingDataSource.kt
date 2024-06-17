package data.repository.datastore.location

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import data.network.ApiService
import data.repository.network.location.model.LocationDetailModelDataResponse
import data.repository.network.location.model.LocationErrorModelDataResponse
import data.repository.network.location.model.LocationModelDataResponse
import domain.location.model.network.LocationDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationPagingDataSource(
  private val httpClient: HttpClient,
  private val apiService: ApiService,
  private val name: String,
  private val type: String,
  private val dimension: String
):PagingSource<Int, LocationDetailModelDomain>() {
  override fun getRefreshKey(state: PagingState<Int, LocationDetailModelDomain>): Int? {
    return null
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDetailModelDomain> {
    val position = params.key ?: 1
    return try {

      val response = httpClient.get("${apiService.BASE_URL_LOCATION}?name=$name&type=$type&dimension=$dimension&page=$position")

      if(response.status.value == 200){
        val apiResponse = response.body<LocationModelDataResponse>()
        val data = LocationDetailModelDataResponse.transforms(apiResponse.results)

        toLoadResult(data = data, nextKey = if(data.isEmpty()) null else position +1)
      }else{
        val apiResponse = response.body<LocationErrorModelDataResponse>()
        val data = LocationErrorModelDataResponse.transforms()

        toLoadResult(data = data, nextKey = if(data.isEmpty()) null else position + 1)
      }



    }catch (e:Exception){
      LoadResult.Error(e)
    }
  }

  private fun toLoadResult(
    data: List<LocationDetailModelDomain>, prevKey: Int? = null, nextKey: Int? = null
  ): LoadResult<Int, LocationDetailModelDomain> {
    return LoadResult.Page(
      data = data, prevKey = prevKey, nextKey = nextKey
    )
  }
}