package presentation.character.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.theme.abuabumuda
import presentation.theme.black
import presentation.util.DefaultAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
  onBackClicked: NavController,
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel,
  characterId: Int,
) {
  Scaffold(
    contentColor = abuabumuda,
    containerColor = abuabumuda,
    modifier = Modifier.fillMaxSize(),
    topBar = {
      DefaultAppBar("Detail Character", true, onBackClicked)
    }
  ) { innerPadding ->
    Text("Detail character", modifier = Modifier.padding(innerPadding), color = black)
  }
}