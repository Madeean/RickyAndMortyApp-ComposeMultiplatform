import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.character.viewmodel.CharacterViewModel
import presentation.location.viewmodel.LocationViewModel

actual val viewModelModule = module {
  viewModelOf(::EpisodeViewModel)
  viewModelOf(::CharacterViewModel)
  viewModelOf(::LocationViewModel)
}