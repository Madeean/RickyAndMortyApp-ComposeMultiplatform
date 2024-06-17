package domain.location.model.network

data class LocationModelDomain (
  val results:List<LocationDetailModelDomain>
)

data class LocationDetailModelDomain (
  val id: Int,
  val name: String,
  val type: String,
  val dimension: String,
  val residents: List<String>,
  val created: String
)
