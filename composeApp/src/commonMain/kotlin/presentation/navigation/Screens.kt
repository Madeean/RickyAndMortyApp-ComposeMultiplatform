package presentation.navigation

import androidx.navigation.NavHostController
import presentation.util.ConstantNavigator

class Screens(navController: NavHostController) {

  val splash:() -> Unit = {
    navController.navigate(route = "splash_screen") {
      popUpTo(ConstantNavigator.SPLASH_SCREEN) { inclusive = true }
    }
  }

  val home: () -> Unit = {
    navController.navigate(route = "home_screen") {
      popUpTo(ConstantNavigator.SPLASH_SCREEN) { inclusive = true }
    }
  }

  val about: () -> Unit = {
    navController.navigate(route = "about")
  }
  val favorite: () -> Unit = {
    navController.navigate(route = "favorite")
  }
  val episodeDetail: (Int) -> Unit = {
    navController.navigate(route = "episode_detail/$it")
  }
  val characterDetail: (Int) -> Unit = {
    navController.navigate(route = "character_detail/$it")
  }
}