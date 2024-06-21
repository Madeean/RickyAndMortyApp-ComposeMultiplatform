package presentation.character.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import presentation.theme.white

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
      Surface(
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
      ) {
        CenterAlignedTopAppBar(
          colors = TopAppBarColors(
            containerColor = biru,
            scrolledContainerColor = biru,
            navigationIconContentColor = white,
            titleContentColor = white,
            actionIconContentColor = white
          ),
          title = {
            Text("Detail Character")
          },
          navigationIcon = {
            IconButton(
              onClick = {
                onBackClicked.navigateUp()
              }
            ) {
              Icon(imageVector = Filled.ArrowBack, contentDescription = null)
            }
          }
        )
      }
    },
  ) { innerPadding ->
    Text("Detail character", modifier = Modifier.padding(innerPadding), color = black)
  }
}