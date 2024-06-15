package presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.character.CharacterScreen
import presentation.episode.EpisodeScreen
import presentation.location.LocationScreen
import presentation.setting.SettingScreen
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.black
import presentation.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  navigateToEpisodeScreen: () -> Unit,
  navigateToCharacterScreen: () -> Unit,
  navigateToLocationScreen: () -> Unit,
  navigateToSettingScreen: () -> Unit,
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
            when(selectedItem){
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
    when(selectedItem){
      0 -> EpisodeScreen(innerPaddingValues = innerPadding)
      1 -> CharacterScreen()
      2 -> LocationScreen()
      3 -> SettingScreen()
    }
  }
}