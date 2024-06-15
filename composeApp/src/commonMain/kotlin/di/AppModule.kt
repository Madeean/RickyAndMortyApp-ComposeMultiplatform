package di

import data.network.ApiService
import data.network.httpClient
import data.repository.domainrepository.EpisodeDomainRepository
import domain.episode.EpisodeDomainUseCase
import domain.episode.EpisodeDomainUseCaseImpl
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.episode.EpisodeViewModel

val appModule = module {
//  includes(networkModule, viewModelModule, repositoryModule)
  single { httpClient }
  single { ApiService(get()) }
  single { EpisodeDomainRepository(get()) }
  single { EpisodeDomainUseCaseImpl(get()) }
  single<EpisodeDomainUseCase> {EpisodeDomainUseCaseImpl(get())}
  factory { EpisodeViewModel(get()) }
}

fun initializeKoin() {
  startKoin {
    appModule
  }
}