package data.repository.datastore.location

import data.network.ApiService
import data.repository.network.location.model.LocationDetailModelDataResponse
import domain.location.model.network.LocationDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import util.RequestState

class LocationDataStore(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {
  suspend fun getDetailLocation(locationId: Int): RequestState<LocationDetailModelDomain> {
    return try{
      val response = httpClient.get("${apiService.BASE_URL_LOCATION}/$locationId")

      if (response.status.value == 200) {
        val apiResponse = response.body<LocationDetailModelDataResponse>()
        val data = LocationDetailModelDataResponse.transform(apiResponse)
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