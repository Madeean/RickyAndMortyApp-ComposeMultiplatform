package data.network

class ApiService {


  private val END_POINT = "https://rickandmortyapi.com/api/"

  private val EPISODE = "episode/"
  val BASE_URL_EPISODE = "$END_POINT$EPISODE"

  private val CHARACTER = "character/"
  val BASE_URL_CHARACTER = "$END_POINT$CHARACTER"

  private val LOCATION = "location/"
  val BASE_URL_LOCATION = "$END_POINT$LOCATION"

}