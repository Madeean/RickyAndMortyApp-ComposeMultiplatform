import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import presentation.episode.EpisodeViewModel
import presentation.navigation.SetupNavigation

@Composable
@Preview
fun App() {
  lateinit var navController: NavHostController
  MaterialTheme {
    KoinContext {
      val viewModel = koinViewModel<EpisodeViewModel>()
      navController = rememberNavController()
      SetupNavigation(navController = navController, episodeViewModel = viewModel)
    }

  }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
  val scope = currentKoinScope()
  return viewModel {
    scope.get<T>()
  }
}