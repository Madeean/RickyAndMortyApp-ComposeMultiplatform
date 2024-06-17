package data.repository.network.location.model

import domain.location.model.network.LocationDetailModelDomain
import kotlinx.serialization.Serializable

@Serializable
data class LocationModelDataResponse (
  val results: List<LocationDetailModelDataResponse>?
)

@Serializable
data class LocationDetailModelDataResponse (
  val id: Int?,
  val name: String?,
  val type: String?,
  val dimension: String?,
  val residents: List<String>?,
  val created: String?
){
  companion object {
    fun transforms(models: List<LocationDetailModelDataResponse>?): List<LocationDetailModelDomain> {
      if(models != null){
        return models.map {
          transform(
            LocationDetailModelDataResponse(
              id = it.id,
              name = it.name,
              type = it.type,
              dimension = it.dimension,
              residents = it.residents,
              created = it.created
            )
          )
        }
      }else{
        return listOf()
      }
    }

    private fun transform(model: LocationDetailModelDataResponse): LocationDetailModelDomain {
      return LocationDetailModelDomain(
        id = model.id ?: 0,
        name = model.name ?: "",
        type = model.type ?: "",
        dimension = model.dimension ?: "",
        residents = model.residents ?: listOf(),
        created = model.created ?: ""
      )
    }
  }
}

@Serializable
data class LocationErrorModelDataResponse(
  val error: String?
){
  companion object{
    fun transforms():List<LocationDetailModelDomain>{
      return listOf()
    }
  }
}
