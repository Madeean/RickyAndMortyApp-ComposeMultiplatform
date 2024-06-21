package presentation.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.character.screen.CharacterScreen
import presentation.character.viewmodel.CharacterViewModel
import presentation.episode.screen.EpisodeScreen
import presentation.episode.viewmodel.EpisodeViewModel
import presentation.location.screen.LocationScreen
import presentation.location.viewmodel.LocationViewModel
import presentation.setting.screen.SettingScreen
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import presentation.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  episodeViewModel: EpisodeViewModel,
  characterViewModel: CharacterViewModel,
  locationViewModel: LocationViewModel,
  navigateToAboutScreen:() ->Unit,
  navigateToFavoriteScreen:() ->Unit,
  navigateToEpisodeDetailScreen:(Int) ->Unit,
  navigateToCharacterDetailScreen:(Int) ->Unit,
) {
  var selectedItem by remember { mutableStateOf(0) }
  val items = listOf("Episode", "Character", "Location", "Settings")

  Scaffold(
    contentColor = abuabumuda,
    containerColor = abuabumuda,
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
            when (selectedItem) {
              0 -> Text("Episode")
              1 -> Text("Character")
              2 -> Text("Location")
              3 -> Text("Settings")
            }
          }
        )
      }
    },
    bottomBar = {
      Surface(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.surface,
      ) {
        NavigationBar(
          containerColor = biru
        ) {
          items.forEachIndexed { index, item ->
            NavigationBarItem(
              alwaysShowLabel = selectedItem == index,
              icon = {
                Icon(
                  imageVector = when (index) {
                    0 -> Icons.Default.Home
                    1 -> Icons.Filled.Person
                    2 -> Icons.Filled.LocationOn
                    else -> Icons.Default.Settings
                  },
                  contentDescription = item
                )
              },
              label = { Text(item) },
              selected = selectedItem == index,
              onClick = { selectedItem = index },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = white,
                unselectedIconColor = black,
                selectedTextColor = white,
                unselectedTextColor = black,
                indicatorColor = Color.Transparent
              )
            )
          }
        }
      }
    }
  ) { innerPadding ->
    when (selectedItem) {
      0 -> EpisodeScreen(innerPaddingValues = innerPadding, viewModel = episodeViewModel,navigateToEpisodeDetailScreen)
      1 -> CharacterScreen(innerPadding, characterViewModel,navigateToCharacterDetailScreen )
      2 -> LocationScreen(innerPadding,locationViewModel)
      3 -> SettingScreen(innerPadding, navigateToAboutScreen, navigateToFavoriteScreen)
    }
  }
}