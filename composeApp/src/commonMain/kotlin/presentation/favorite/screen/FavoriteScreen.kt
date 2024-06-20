package presentation.favorite.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import presentation.favorite.screen.tabscreens.FavoriteCharacterScreen
import presentation.favorite.screen.tabscreens.FavoriteEpisodeScreen
import presentation.favorite.screen.tabscreens.FavoriteLocationScreen
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(onBackClicked: NavController) {
  val tabItems = listOf(
    TabItem("Episode"),
    TabItem("Character"),
    TabItem("Location"),
  )
  var selectedTabIndex by remember { mutableStateOf(0) }

  Scaffold(
    modifier = Modifier.fillMaxSize().background(abuabumuda),
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
            Text("Daftar Favorite")
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
    }
  ) {
    Column(modifier = Modifier.padding(it)) {
      TabRow(
        selectedTabIndex = selectedTabIndex
      ) {
        tabItems.forEachIndexed { index, item ->
          Tab(
            selected = index == selectedTabIndex,
            onClick = {
              selectedTabIndex = index
            },
            text = {
              Text(item.title)
            },
          )
        }
      }
      when(selectedTabIndex){
        0 -> FavoriteEpisodeScreen()
        1 -> FavoriteCharacterScreen()
        2 -> FavoriteLocationScreen()
      }
    }
  }
}

data class TabItem(
  val title: String,
)