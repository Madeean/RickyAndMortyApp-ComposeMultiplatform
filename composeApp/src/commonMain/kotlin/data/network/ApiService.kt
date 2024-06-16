package data.network

import io.ktor.client.HttpClient

class ApiService {


  private val END_POINT = "https://rickandmortyapi.com/api/"

  private val EPISODE = "episode/"
  val BASE_URL_EPISODE = "$END_POINT$EPISODE"

  private val CHARACTER = "character/"
  val BASE_URL_CHARACTER = "$END_POINT$CHARACTER"

}