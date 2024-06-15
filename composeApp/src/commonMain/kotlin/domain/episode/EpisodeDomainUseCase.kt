package domain.episode

import io.ktor.http.ContentType.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface EpisodeDomainUseCase {
  suspend fun getAllEpisode(
    scope: CoroutineScope,
    name:String
  )

  suspend fun getMessage(): Flow<String>
}