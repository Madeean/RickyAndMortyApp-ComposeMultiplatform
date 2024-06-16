package domain.episode.model.network

data class EpisodeModelDomain(
  val results: EpisodeDetailModelDomain,
)

data class EpisodeDetailModelDomain(
  val id:Int,
  val name:String,
  val airDate:String,
  val episode:String,
  val created:String,
  val characterList:List<String>,
)