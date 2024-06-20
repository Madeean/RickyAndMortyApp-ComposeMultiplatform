package presentation.favorite.screen.tabscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteCharacterScreen(modifier: Modifier = Modifier) {
  Column(modifier=Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
    Text("favorite character")
  }
}