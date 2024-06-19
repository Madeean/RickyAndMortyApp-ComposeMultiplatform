package presentation.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.theme.abuabumuda
import presentation.theme.biru
import presentation.theme.white
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.penjelasan_rickandmorty
import rickandmortyapp.composeapp.generated.resources.rickandmorty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onBackClicked: NavController) {
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
            Text("About this Application")
          },
          navigationIcon = {
            IconButton(
              onClick = {
                onBackClicked.navigateUp()
              }
            ) {
              Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
          }
        )
      }
    },
  ) {
    Column(modifier = Modifier.padding(it)) {
      Image(painter = painterResource(Res.drawable.rickandmorty), contentDescription = null)
      Text(text = stringResource(Res.string.penjelasan_rickandmorty), modifier = Modifier.padding(horizontal = 12.dp),
        textAlign = TextAlign.Justify
        )
    }
  }
}