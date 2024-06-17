package di

import data.network.ApiService
import data.network.httpClient
import data.repository.domainrepository.CharacterDomainRepositoryImpl
import data.repository.domainrepository.EpisodeDomainRepositoryImpl
import data.repository.domainrepository.LocationDomainRepositoryImpl
import domain.character.CharacterDomainRepository
import domain.character.CharacterDomainUseCase
import domain.character.CharacterDomainUseCaseImpl
import domain.episode.EpisodeDomainRepository
import domain.episode.EpisodeDomainUseCase
import domain.episode.EpisodeDomainUseCaseImpl
import domain.location.LocationDomainRepository
import domain.location.LocationDomainUseCase
import domain.location.LocationDomainUseCaseImpl
import org.koin.dsl.module
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.location.viewmodel.LocationViewModel

val appModule = module {
  single { httpClient }
  single { ApiService() }

//  Episode Repository
  single { EpisodeDomainRepositoryImpl(get(),get()) }
  single { EpisodeDomainUseCaseImpl(get()) }
  single<EpisodeDomainUseCase> { EpisodeDomainUseCaseImpl(get()) }
  single<EpisodeDomainRepository> { EpisodeDomainRepositoryImpl(get(),get()) }

//  Episode ViewModel
  factory { EpisodeViewModel(get()) }

//  Character Repository
  single { CharacterDomainRepositoryImpl(get(),get()) }
  single { CharacterDomainUseCaseImpl(get()) }
  single<CharacterDomainUseCase> { CharacterDomainUseCaseImpl(get()) }
  single<CharacterDomainRepository> { CharacterDomainRepositoryImpl(get(),get()) }
//  Character viewmodel
  factory { CharacterViewModel(get()) }

//  LOCATION REPOSITORY
  single { LocationDomainRepositoryImpl(get(),get()) }
  single { LocationDomainUseCaseImpl(get()) }
  single<LocationDomainUseCase> { LocationDomainUseCaseImpl(get()) }
  single<LocationDomainRepository> { LocationDomainRepositoryImpl(get(),get()) }
//  LOCATION VIEWMODEL
  factory { LocationViewModel(get()) }
}