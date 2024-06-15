package domain.episode

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface EpisodeDomainRepository {
  suspend fun getAllEpisode(
    scope: CoroutineScope,
    name:String
  )

  suspend fun getMessage(): Flow<String>

}