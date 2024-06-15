package data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

  companion object {
    private const val END_POINT = "https://rickandmortyapi.com/api/"
    private const val EPISODE = "episode"
  }

  suspend fun getEpisode() = client.get("$END_POINT$EPISODE")
}