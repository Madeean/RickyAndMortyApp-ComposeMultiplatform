package presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.location.viewmodel.LocationViewModel
import presentation.navigation.directions.HomeComposable
import presentation.navigation.directions.aboutComposable
import presentation.navigation.directions.favoriteComposable
import presentation.navigation.directions.splashComposable
import presentation.util.ConstantNavigator

@Composable
fun SetupNavigation(
  navController: NavHostController,
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel,
  locationViewModel: LocationViewModel
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  NavHost(
    navController = navController,
    startDestination = ConstantNavigator.SPLASH_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ){
    splashComposable(
      navigateToHomeScreen =  screen.home
    )
    HomeComposable(
      episodeViewModel = episodeViewModel,
      characterViewModel= characterViewModel,
      locationViewModel = locationViewModel,
      navigateToAboutScreen = screen.about,
      navigateToFavoriteScreen = screen.favorite
    )
    aboutComposable(
      onBackClicked = navController
    )
    favoriteComposable(
      onBackClicked = navController
    )
  }
}