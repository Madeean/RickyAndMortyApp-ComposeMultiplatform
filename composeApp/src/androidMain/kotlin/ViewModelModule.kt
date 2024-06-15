import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.episode.EpisodeViewModel

actual val viewModelModule = module {
  viewModelOf(::EpisodeViewModel)
}