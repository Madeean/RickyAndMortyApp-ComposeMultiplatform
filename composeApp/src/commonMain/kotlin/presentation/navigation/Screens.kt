package presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import presentation.util.ConstantNavigator

class Screens(navController: NavHostController) {

  val splash:() -> Unit = {
    navController.navigate(route = "splash_screen"){
      popUpTo(ConstantNavigator.SPLASH_SCREEN) {inclusive = true}
    }
  }

  val setting: () -> Unit =  {
    navController.navigate(route = "setting_screen")
  }

  val episode: () -> Unit = {
    navController.navigate(route = "episode_screen")
  }

  val character: () -> Unit = {
    navController.navigate(route = "character_screen")
  }

  val location: () -> Unit = {
    navController.navigate(route = "location_screen")
  }

  val home: () -> Unit = {
    navController.navigate(route = "home_screen")
  }
}