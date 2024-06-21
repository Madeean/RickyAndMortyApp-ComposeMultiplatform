package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.character.screen.detail.CharacterDetailScreen
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.util.ConstantNavigator

fun NavGraphBuilder.characterDetailComposable(
  onBackClicked: NavController,
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel
) {
  composable(
    route = ConstantNavigator.CHARACTER_DETAIL_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None },
    arguments = listOf(navArgument(ConstantNavigator.CHARACTER_DETAIL_ARGUMENT_KEY) {
      type = NavType.IntType
    })
  ) {
    val characterId = it.arguments?.getInt(ConstantNavigator.CHARACTER_DETAIL_ARGUMENT_KEY) ?: 0
    CharacterDetailScreen(onBackClicked, episodeViewModel, characterViewModel, characterId)
  }
}