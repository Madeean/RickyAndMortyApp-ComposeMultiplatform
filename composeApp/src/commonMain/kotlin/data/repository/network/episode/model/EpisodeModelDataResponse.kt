package data.repository.network.episode.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class EpisodeModelDataResponse(
  val results : List<EpisodeDetailModelDataResponse>
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
//  companion object{
//    fun transforms(models:List<EpisodeDetail>?):List<EpisodeModelItemModel>{
//      if (models != null) {
//        return models.map{
//          transform(
//            EpisodeDetail(
//              it.id,
//              it.name,
//              it.airDate,
//              it.episode,
//              it.created,
//              it.characterList
//            ),
//          )
//        }
//      }else{
//        return listOf()
//      }
//    }
//
//    private fun transform(model:EpisodeDetail?): EpisodeModelItemModel {
//      return EpisodeModelItemModel(
//        model?.id,
//        model?.name,
//        model?.airDate,
//        model?.episode,
//        model?.created,
//        model?.characterList,
//      )
//    }
//  }
}
