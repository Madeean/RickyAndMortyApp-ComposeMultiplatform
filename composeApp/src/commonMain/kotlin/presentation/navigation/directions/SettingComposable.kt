package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.setting.SettingScreen
import presentation.splash.SplashScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.SettingComposable(
  navigateToEpisodeScreen:() -> Unit,
  navigateToCharacterScreen:() -> Unit,
  navigateToLocationScreen:() -> Unit
){
  composable(
    route = ConstantNavigator.SETTING_HOME_SCREEN
  ){
    SettingScreen()
  }
}
