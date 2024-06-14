package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.home.HomeScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.HomeComposable(
  navigateToEpisodeScreen:() -> Unit,
  navigateToCharacterScreen:() -> Unit,
  navigateToLocationScreen:() -> Unit,
  navigateToSettingScreen:() -> Unit,
){
  composable(
    route = ConstantNavigator.HOME_SCREEN
  ){
    HomeScreen(
      navigateToEpisodeScreen = navigateToEpisodeScreen,
      navigateToCharacterScreen = navigateToCharacterScreen,
      navigateToSettingScreen = navigateToSettingScreen,
      navigateToLocationScreen = navigateToLocationScreen
    )
  }
}