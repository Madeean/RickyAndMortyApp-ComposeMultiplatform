package data.repository.network.episode.model

import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeModelDataResponse(
  @SerialName("results") val results : List<EpisodeDetailModelDataResponse>?
)
@Serializable
data class EpisodeDetailModelDataResponse(
  val id:Int?,
  val name:String?,
  @SerialName("air_date") val airDate:String?,
  val episode:String?,
  val created:String?,
  @SerialName("characters") val characterList:List<String>?,
){
  companion object{
    fun transforms(models:List<EpisodeDetailModelDataResponse>?):List<EpisodeDetailModelDomain>{
      if (models != null) {
        return models.map{
          transform(
            EpisodeDetailModelDataResponse(
              it.id ?: 0,
              it.name ?: "",
              it.airDate ?: "",
              it.episode ?: "",
              it.created ?: "",
              it.characterList ?: listOf()
            ),
          )
        }
      }else{
        return listOf()
      }
    }

    private fun transform(model:EpisodeDetailModelDataResponse?): EpisodeDetailModelDomain {
      return EpisodeDetailModelDomain(
        model?.id ?: 0,
        model?.name ?: "",
        model?.airDate ?: "",
        model?.episode?:"",
        model?.created?:"",
        model?.characterList?: listOf(),
      )
    }
  }
}
