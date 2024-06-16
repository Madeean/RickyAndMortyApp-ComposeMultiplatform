package data.repository.datastore.episode

import data.network.ApiService
import data.repository.network.episode.model.EpisodeDetailModelDataResponse
import data.repository.network.episode.model.EpisodeModelDataResponse
import domain.episode.model.network.EpisodeDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import util.RequestState

class EpisodeDataStore(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {
  suspend fun getAllEpisode(): RequestState<List<EpisodeDetailModelDomain>> {
    println("MASUK 3")
    return try {
      val response: HttpResponse = httpClient.get(apiService.BASE_URL_EPISODE)
      println("MASUK 4 $response")

      if (response.status.value == 200) {
        val apiResponse = response.body<EpisodeModelDataResponse>()
        val data = EpisodeDetailModelDataResponse.transforms(apiResponse.results)
        println("MASUK 5 $data")
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    } catch (e: Exception) {
      println("MASUK 6 $e")
      RequestState.Error(e)
    }
  }
}