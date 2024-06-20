package data.repository.network.character.model

import domain.character.model.network.CharacterDetailLocationModelDomain
import domain.character.model.network.CharacterDetailModelDomain
import domain.character.model.network.CharacterDetailOriginModelDomain
import kotlinx.serialization.Serializable

@Serializable
data class CharacterModelDataResponse (
  val results:List<CharacterDetailModelDataResponse>?
)
@Serializable
data class CharacterDetailModelDataResponse (
  val id:Int?,
  val name:String?,
  val status:String?,
  val species:String?,
  val type:String?,
  val gender:String?,
  val origin:CharacterDetailOriginModelDataResponse?,
  val location:CharacterDetailLocationModelDataResponse?,
  val image:String?,
  val episode:List<String>?,
  val created:String?
){
  companion object{
    fun transforms(models:List<CharacterDetailModelDataResponse>?):List<CharacterDetailModelDomain>{
      if(models != null){
        return models.map{
          transform(
            CharacterDetailModelDataResponse(
              id = it.id,
              name = it.name,
              status = it.status,
              species = it.species,
              type = it.type,
              gender = it.gender,
              origin = it.origin,
              location = it.location,
              image = it.image,
              episode = it.episode,
              created = it.created
            )
          )
        }
      }else{
        return listOf()
      }
    }

    fun transform(model:CharacterDetailModelDataResponse): CharacterDetailModelDomain {
      return CharacterDetailModelDomain(
        id = model.id?: 0,
        name = model.name ?: "",
        status = model.status ?: "",
        species = model.species ?: "",
        type = model.type ?: "",
        gender = model.gender ?:"",
        origin = CharacterDetailOriginModelDomain(
          name = model.origin?.name ?: "",
          url = model.origin?.url ?: ""
        ),
        location = CharacterDetailLocationModelDomain(
          name = model.location?.name ?: "",
          url = model.location?.url ?: ""
        ),
        image = model.image ?: "",
        episode = model.episode ?: listOf(),
        created = model.created ?: ""
      )
    }
  }
}
@Serializable
data class CharacterDetailOriginModelDataResponse(
  val name:String?,
  val url:String?
)
@Serializable
data class CharacterDetailLocationModelDataResponse(
  val name:String?,
  val url:String?
)

@Serializable
data class CharacterErrorModelDataResponse(
  val error: String?
){
  companion object{
    fun transforms(models: CharacterErrorModelDataResponse?):List<CharacterDetailModelDomain>{
      return listOf()
    }
  }
}
