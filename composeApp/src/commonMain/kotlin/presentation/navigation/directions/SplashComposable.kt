package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.splash.SplashScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.splashComposable(
  navigateToHomeScreen:() -> Unit
){
  composable(
    route = ConstantNavigator.SPLASH_SCREEN
  ){
    SplashScreen(navigateToHomeScreen)
  }
}