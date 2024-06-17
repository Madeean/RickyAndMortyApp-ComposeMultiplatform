package presentation.navigation

import androidx.navigation.NavHostController
import presentation.util.ConstantNavigator

class Screens(navController: NavHostController) {

  val splash:() -> Unit = {
    navController.navigate(route = "splash_screen"){
      popUpTo(ConstantNavigator.SPLASH_SCREEN) {inclusive = true}
    }
  }

  val home: () -> Unit = {
    navController.navigate(route = "home_screen")
  }
}