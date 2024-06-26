package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.splash.SplashScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.splashComposable(
  navigateToHomeScreen:() -> Unit
){
  composable(
    route = ConstantNavigator.SPLASH_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ){
    SplashScreen(navigateToHomeScreen)
  }
}