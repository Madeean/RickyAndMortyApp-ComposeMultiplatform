package presentation.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.character.CharacterScreen
import presentation.episode.EpisodeScreen
import presentation.location.LocationScreen
import presentation.setting.SettingScreen

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
    bottomBar = {
      Surface(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.surface,
      ) {
        NavigationBar {
          items.forEachIndexed { index, item ->
            NavigationBarItem(
              alwaysShowLabel = selectedItem == index,
              icon = {
                Icon(
                  imageVector = when (index) {
                    0 -> Icons.Default.Home
                    1 -> Icons.Default.Search
                    2 -> Icons.Default.AccountCircle
                    else -> Icons.Default.Settings
                  },
                  contentDescription = item
                )
              },
              label = { Text(item) },
              selected = selectedItem == index,
              onClick = { selectedItem = index },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Gray,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Gray,
                indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
              )
            )
          }
        }
      }
    }
  ) { innerPadding ->
    when(selectedItem){
      0 -> EpisodeScreen()
      1 -> CharacterScreen()
      2 -> LocationScreen()
      3 -> SettingScreen()
    }
  }
}