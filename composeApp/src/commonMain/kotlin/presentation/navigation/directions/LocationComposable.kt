package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.location.LocationScreen
import presentation.splash.SplashScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.LocationComposable(
  navigateToEpisodeScreen:() -> Unit,
  navigateToCharacterScreen:() -> Unit,
  navigateToSettingScreen:() -> Unit
){
  composable(
    route = ConstantNavigator.LOCATION_HOME_SCREEN
  ){
    LocationScreen()
  }
}