package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import presentation.navigation.directions.CharacterComposable
import presentation.navigation.directions.EpisodeComposable
import presentation.navigation.directions.HomeComposable
import presentation.navigation.directions.LocationComposable
import presentation.navigation.directions.SettingComposable
import presentation.navigation.directions.splashComposable
import presentation.util.ConstantNavigator

@Composable
fun SetupNavigation(
  navController: NavHostController,
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  NavHost(
    navController = navController,
    startDestination = ConstantNavigator.SPLASH_SCREEN
  ){
    splashComposable(
      navigateToHomeScreen =  screen.home
    )
    SettingComposable(
      navigateToLocationScreen = screen.location,
      navigateToEpisodeScreen = screen.episode,
      navigateToCharacterScreen = screen.character
    )
    LocationComposable(
      navigateToEpisodeScreen = screen.episode,
      navigateToCharacterScreen = screen.character,
      navigateToSettingScreen = screen.setting
    )
    CharacterComposable(
      navigateToEpisodeScreen = screen.episode,
      navigateToSettingScreen = screen.setting,
      navigateToLocationScreen = screen.location
    )
    EpisodeComposable(
      navigateToCharacterScreen = screen.character,
      navigateToSettingScreen = screen.setting,
      navigateToLocationScreen = screen.location
    )
    HomeComposable(
      navigateToSettingScreen = screen.setting,
      navigateToLocationScreen = screen.location,
      navigateToCharacterScreen = screen.character,
      navigateToEpisodeScreen = screen.episode
    )
  }
}