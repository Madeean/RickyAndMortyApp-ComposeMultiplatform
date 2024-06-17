import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.character.viewmodel.CharacterViewModel
import presentation.location.viewmodel.LocationViewModel

actual val viewModelModule = module {
  singleOf(::EpisodeViewModel)
  singleOf(::CharacterViewModel)
  singleOf(::LocationViewModel)
}