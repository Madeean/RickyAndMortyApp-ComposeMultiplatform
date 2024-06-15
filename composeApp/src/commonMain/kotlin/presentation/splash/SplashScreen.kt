package presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.rickandmorty

@Composable
fun SplashScreen(
  navigateToHomeScreen:() -> Unit
) {
  val gradientColors = listOf(Color(0xFF50CE8A), Color(0xFF5066CE))
  val gradientBrush = Brush.linearGradient(
    colors = gradientColors,
    start = androidx.compose.ui.geometry.Offset(0f, 0f),
    end = androidx.compose.ui.geometry.Offset(1000f, 1000f)
  )

  LaunchedEffect(key1 = true){
    delay(1000)
    navigateToHomeScreen()
  }

  Box(
    modifier = Modifier.fillMaxSize().background(gradientBrush),
    contentAlignment = Alignment.Center
  ){
    Image(painter = painterResource(Res.drawable.rickandmorty), contentDescription = null)
  }
}