package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Teal200 = Color(0xFF03DAC5)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = MediumGray

val purple_200 =Color(0xFFBB86FC)
val purple_500 =Color(0xFF6200EE)
val purple_700 =Color(0xFF3700B3)
val teal_200 =Color(0xFF03DAC5)
val teal_700 =Color(0xFF018786)
val black =Color(0xFF000000)
val white =Color(0xFFFFFFFF)

val biru = Color(0xFF5066CE)
val abuabutua = Color(0xFFD9D9D9)
val abuabumuda = Color(0xFFE6E6E6)
val hitam = Color(0xFF353535)
val hijau = Color(0xFF50CE8A)
val ungu = Color(0xFFAE7EFD)
val merah = Color(0xFFF55C5C)

val darkmode_biru_tua = Color(0xFF0B2447)
val darkmode_biru_tua_sedikit_muda = Color(0xFF19376D)
val darkmode_biru_muda = Color(0xFF576CBC)
val darkmode_biru_sangat_muda = Color(0xFFA5D7E8)



val ColorScheme.splashScreenBackground: Color
  @Composable
  get() = if (isSystemInDarkTheme()) Purple80 else Color.Black

val ColorScheme.taskItemTextColor: Color
  @Composable
  get() = if (isSystemInDarkTheme()) DarkGray else LightGray

val ColorScheme.taskItemBackgroundColor: Color
  @Composable
  get() = if (isSystemInDarkTheme()) Color.White else DarkGray

val ColorScheme.topAppBarContentColor: Color
  @Composable
  get() = if (isSystemInDarkTheme()) Color.White else LightGray

val ColorScheme.topAppBarBackgroundColor: Color
  @Composable
  get() = if (isSystemInDarkTheme()) Purple80 else Color.Black
val ColorScheme.fabBackgroundColor: Color
  @Composable
  get() = if (isSystemInDarkTheme()) Teal200 else Purple80