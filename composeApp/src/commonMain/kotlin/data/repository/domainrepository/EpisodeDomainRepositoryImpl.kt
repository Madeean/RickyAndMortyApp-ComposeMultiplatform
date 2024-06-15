package data.repository.domainrepository

import data.network.ApiService
import domain.episode.EpisodeDomainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EpisodeDomainRepositoryImpl(
private val apiService: ApiService
): EpisodeDomainRepository {
  override suspend fun getAllEpisode(scope: CoroutineScope, name: String) {

  }

  override suspend fun getMessage(): Flow<String> = flow {
    emit("Made Reihan")
  }

}