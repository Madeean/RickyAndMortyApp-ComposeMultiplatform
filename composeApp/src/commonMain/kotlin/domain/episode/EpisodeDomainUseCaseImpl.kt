package domain.episode

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class EpisodeDomainUseCaseImpl(
  private val repository: EpisodeDomainRepository
):EpisodeDomainUseCase {
  override suspend fun getAllEpisode(scope: CoroutineScope, name: String) {
    repository.getAllEpisode(scope,name)
  }

  override suspend fun getMessage(): Flow<String> {
    return repository.getMessage()
  }

}