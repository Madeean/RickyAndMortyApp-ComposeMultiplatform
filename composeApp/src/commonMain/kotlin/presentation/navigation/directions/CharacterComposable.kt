package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.character.CharacterScreen
import presentation.splash.SplashScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.CharacterComposable(
  navigateToEpisodeScreen:() -> Unit,
  navigateToLocationScreen:() -> Unit,
  navigateToSettingScreen:() -> Unit,
){
  composable(
    route = ConstantNavigator.CHARACTER_HOME_SCREEN
  ){
    CharacterScreen()
  }
}