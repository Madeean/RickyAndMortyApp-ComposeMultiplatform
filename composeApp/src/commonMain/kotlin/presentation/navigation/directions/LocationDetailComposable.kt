package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.character.viewmodel.CharacterViewModel
import presentation.location.screen.detail.LocationDetailScreen
import presentation.location.viewmodel.LocationViewModel
import presentation.util.ConstantNavigator

fun NavGraphBuilder.locationDetailComposable(
  onBackClicked: NavController,
  locationViewModel: LocationViewModel,
  characterViewModel: CharacterViewModel,
  navigateToCharacterDetailScreen: (Int) -> Unit
) {
  composable(
    route = ConstantNavigator.LOCATION_DETAIL_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None },
    arguments = listOf(navArgument(ConstantNavigator.LOCATION_DETAIL_ARGUMENT_KEY) {
      type = NavType.IntType
    })
  ) {
    val locationId = it.arguments?.getInt(ConstantNavigator.LOCATION_DETAIL_ARGUMENT_KEY) ?: 0
    LocationDetailScreen(onBackClicked, locationViewModel, characterViewModel, locationId, navigateToCharacterDetailScreen)
  }
}