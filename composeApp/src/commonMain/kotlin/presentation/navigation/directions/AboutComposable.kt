package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.about.AboutScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.aboutComposable(){
  composable(
    route = ConstantNavigator.ABOUT_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ) {
    AboutScreen()
  }
}