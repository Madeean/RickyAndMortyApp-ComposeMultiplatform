package domain.episode

import domain.episode.model.network.EpisodeDetailModelDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import util.RequestState

interface EpisodeDomainRepository {
   fun getAllEpisode(
    name:String,
    viewmodelScope: CoroutineScope
  ):Flow<RequestState<List<EpisodeDetailModelDomain>>>

  suspend fun getMessage(): Flow<String>

}