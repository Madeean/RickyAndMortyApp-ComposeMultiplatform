package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.home.HomeScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.HomeComposable(
  viewModel: EpisodeViewModel
){
  composable(
    route = ConstantNavigator.HOME_SCREEN
  ){
    HomeScreen(
      viewModel = viewModel
    )
  }
}