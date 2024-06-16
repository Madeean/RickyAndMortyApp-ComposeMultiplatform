package domain.character.model.network

data class CharacterModelDomain(
  val results:List<CharacterDetailModelDomain>
)

data class CharacterDetailModelDomain(
  val id:Int,
  val name:String,
  val status:String,
  val species:String,
  val type:String,
  val gender:String,
  val origin: CharacterDetailOriginModelDomain,
  val location: CharacterDetailLocationModelDomain,
  val image:String,
  val episode:List<String>,
  val created:String
)

data class CharacterDetailOriginModelDomain(
  val name:String,
  val url:String
)
data class CharacterDetailLocationModelDomain(
  val name:String,
  val url:String
)
