package data.repository.datastore.episode

import data.network.ApiService
import data.repository.network.episode.model.EpisodeDetailModelDataResponse
import domain.episode.model.network.EpisodeDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import util.RequestState

class EpisodeDataStore(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {

  suspend fun getDetailEpisode(episodeId: Int): RequestState<EpisodeDetailModelDomain> {
    return try {
      val response = httpClient.get("${apiService.BASE_URL_EPISODE}/$episodeId")

      if (response.status.value == 200) {
        val apiResponse = response.body<EpisodeDetailModelDataResponse>()
        val data = EpisodeDetailModelDataResponse.transform(apiResponse)
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    } catch (e: Exception) {
      RequestState.Error(e)
    }
  }

  suspend fun getListEpisodeFromUrl(url: String): RequestState<List<EpisodeDetailModelDomain>>{
    return try{
      val response = httpClient.get("${apiService.BASE_URL_EPISODE}/$url")

      if (response.status.value == 200) {
        val apiResponse = response.body<List<EpisodeDetailModelDataResponse>>()
        val data = EpisodeDetailModelDataResponse.transforms(apiResponse)
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    }catch (e: Exception) {
      RequestState.Error(e)
    }
  }
}