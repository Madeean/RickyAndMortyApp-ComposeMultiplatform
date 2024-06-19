package presentation.setting.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.setting.model.SettingModelPresentation
import presentation.theme.black
import presentation.theme.white

@Composable
fun SettingScreen(
  innerPaddingValues: PaddingValues,
  navigateToAboutScreen: () -> Unit,
  navigateToFavoriteScreen: () -> Unit,
) {

  val data: List<SettingModelPresentation> = listOf(
    SettingModelPresentation(
      imageVector = Icons.Outlined.Favorite,
      title = "Favorite list",
      isDarkMode = false,
      isNavigate = true,
      navigate = navigateToFavoriteScreen
    ),
    SettingModelPresentation(
      imageVector = Icons.Outlined.Warning,
      title = "About this application",
      isDarkMode = false,
      isNavigate = true,
      navigate = navigateToAboutScreen
    ),
    SettingModelPresentation(
      imageVector = Icons.Outlined.Star,
      title = "Dark mode",
      isDarkMode = true,
      isNavigate = false,
      navigate = {}
    )
  )

  Column(
    modifier = Modifier.padding(innerPaddingValues).fillMaxSize()
  ) {
    LazyColumn {
      items(data.count()) {
        val item = data[it]
        SettingItems(
          imageVector = item.imageVector,
          title = item.title,
          isDarkMode = item.isDarkMode,
          isNavigate = item.isNavigate,
          navigate = item.navigate
        )
        Divider(color = black)
      }
    }
  }
}

@Composable
fun SettingItems(
  imageVector: ImageVector,
  title: String,
  isDarkMode: Boolean,
  isNavigate: Boolean,
  navigate: () -> Unit
) {
  var checked by remember { mutableStateOf(false) }
  Row(
    modifier = Modifier.fillMaxWidth().background(white).padding(10.dp).clickable {
      if (isNavigate) {
        navigate()
      }
    }
  ) {
    Icon(imageVector = imageVector, contentDescription = null)
    Spacer(modifier = Modifier.width(20.dp))
    Text(text = title, color = black, fontSize = 20.sp)
    Spacer(modifier = Modifier.weight(1f))
    if (isDarkMode) {
      Switch(
        modifier = Modifier.size(10.dp).align(Alignment.CenterVertically),
        checked = checked,
        onCheckedChange = {
          checked = it
        }
      )
      Spacer(modifier = Modifier.width(20.dp))
    } else {
      Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
    }
  }
}