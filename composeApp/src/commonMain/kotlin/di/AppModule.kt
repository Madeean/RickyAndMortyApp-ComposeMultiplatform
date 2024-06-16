package di

import data.network.ApiService
import data.network.httpClient
import data.repository.datastore.EpisodeDataStore
import data.repository.domainrepository.EpisodeDomainRepositoryImpl
import domain.episode.EpisodeDomainRepository
import domain.episode.EpisodeDomainUseCase
import domain.episode.EpisodeDomainUseCaseImpl
import org.koin.dsl.module
import presentation.episode.viewmodel.EpisodeViewModel

val appModule = module {
  single { httpClient }
  single { ApiService(get()) }
  single { EpisodeDomainRepositoryImpl(get(),get()) }
  single { EpisodeDomainUseCaseImpl(get()) }
  single<EpisodeDomainUseCase> { EpisodeDomainUseCaseImpl(get()) }
  single<EpisodeDomainRepository> { EpisodeDomainRepositoryImpl(get(),get()) }
  single{EpisodeDataStore(get(), get())}
  factory { EpisodeViewModel(get()) }
}