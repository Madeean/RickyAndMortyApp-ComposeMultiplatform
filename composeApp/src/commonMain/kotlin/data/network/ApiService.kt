package data.network

import io.ktor.client.HttpClient

class ApiService(private val client: HttpClient) {


  val END_POINT = "https://rickandmortyapi.com/api/"
  val EPISODE = "episode"
  val BASE_URL_EPISODE = "$END_POINT$EPISODE"


}