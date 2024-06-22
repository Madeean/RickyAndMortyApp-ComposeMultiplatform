package presentation.navigation.directions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.home.HomeScreen
import presentation.location.viewmodel.LocationViewModel
import presentation.util.ConstantNavigator

fun NavGraphBuilder.HomeComposable(
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel,
  locationViewModel: LocationViewModel,
  navigateToAboutScreen:() ->Unit,
  navigateToFavoriteScreen:() ->Unit,
  navigateToEpisodeDetailScreen:(Int) ->Unit,
  navigateToCharacterDetailScreen:(Int) ->Unit,
  navigateToLocationDetailScreen:(Int) ->Unit,
//  navigateToEpisodeDetailScreen:(detailEpisode: EpisodeDetailModelDomain) ->Unit,
){
  composable(
    route = ConstantNavigator.HOME_SCREEN,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ){
    HomeScreen(
      episodeViewModel = episodeViewModel,
      characterViewModel = characterViewModel,
      locationViewModel = locationViewModel,
      navigateToAboutScreen = navigateToAboutScreen,
      navigateToFavoriteScreen = navigateToFavoriteScreen,
      navigateToEpisodeDetailScreen = navigateToEpisodeDetailScreen,
      navigateToCharacterDetailScreen = navigateToCharacterDetailScreen,
      navigateToLocationDetailScreen = navigateToLocationDetailScreen
    )
  }
}