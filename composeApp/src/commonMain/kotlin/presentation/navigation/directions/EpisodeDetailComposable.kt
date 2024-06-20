package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.screen.detail.EpisodeDetailScreen
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.util.ConstantNavigator

fun NavGraphBuilder.episodeDetailComposable(
  onBackClicked: NavController,
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel
) {
  composable(
    route = ConstantNavigator.EPISODE_DETAIL_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None },
    arguments = listOf(navArgument(ConstantNavigator.EPISODE_DETAIL_ARGUMENT_KEY) {
      type = NavType.IntType
    })
  ) {
    val episodeId = it.arguments?.getInt(ConstantNavigator.EPISODE_DETAIL_ARGUMENT_KEY) ?: 0
    EpisodeDetailScreen(onBackClicked, episodeViewModel, characterViewModel, episodeId)
  }
}