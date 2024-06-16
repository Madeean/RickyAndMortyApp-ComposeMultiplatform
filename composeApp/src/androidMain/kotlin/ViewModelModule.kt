import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.character.viewmodel.CharacterViewModel

actual val viewModelModule = module {
  viewModelOf(::EpisodeViewModel)
  viewModelOf(::CharacterViewModel)
}