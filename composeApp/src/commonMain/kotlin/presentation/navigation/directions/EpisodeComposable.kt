package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.episode.EpisodeScreen
import presentation.splash.SplashScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.EpisodeComposable(
  navigateToCharacterScreen:() -> Unit,
  navigateToLocationScreen:() -> Unit,
  navigateToSettingScreen:() -> Unit,
){
  composable(
    route = ConstantNavigator.EPISODE_HOME_SCREEN
  ){
  }
}