package data.repository.datastore.character

import data.network.ApiService
import data.repository.network.character.model.CharacterDetailModelDataResponse
import domain.character.model.network.CharacterDetailModelDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import util.RequestState

class CharacterDataSource(
  private val httpClient: HttpClient,
  private val apiService: ApiService
) {
  suspend fun getListCharacter(characterId: String): RequestState<List<CharacterDetailModelDomain>> {
    return try{
      val response = httpClient.get("${apiService.BASE_URL_CHARACTER}/$characterId")

      if (response.status.value == 200) {
        val apiResponse = response.body<List<CharacterDetailModelDataResponse>>()
        val data = CharacterDetailModelDataResponse.transforms(apiResponse)
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    }catch (e:Exception){
      RequestState.Error(e)
    }
  }

  suspend fun getDetailCharacter(characterId: Int): RequestState<CharacterDetailModelDomain>{
    return try{
      val response = httpClient.get("${apiService.BASE_URL_CHARACTER}/$characterId")

      if (response.status.value == 200) {
        val apiResponse = response.body<CharacterDetailModelDataResponse>()
        val data = CharacterDetailModelDataResponse.transform(apiResponse)
        RequestState.Success(data)
      } else {
        val error = Throwable(message = "HTTP ERROR ${response.status.value}")
        RequestState.Error(error)
      }
    }catch (e:Exception){
      RequestState.Error(e)
    }
  }
}