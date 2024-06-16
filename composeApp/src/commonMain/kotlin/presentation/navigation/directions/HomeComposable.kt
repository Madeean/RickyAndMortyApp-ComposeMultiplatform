package presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.home.HomeScreen
import presentation.util.ConstantNavigator

fun NavGraphBuilder.HomeComposable(
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel
){
  composable(
    route = ConstantNavigator.HOME_SCREEN
  ){
    HomeScreen(
      episodeViewModel = episodeViewModel,
      characterViewModel = characterViewModel
    )
  }
}