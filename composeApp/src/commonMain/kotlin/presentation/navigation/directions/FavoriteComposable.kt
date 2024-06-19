package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.favorite.FavoriteScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.favoriteComposable() {
  composable(
    route = ConstantNavigator.FAVORITE_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ) {
    FavoriteScreen()
  }
}