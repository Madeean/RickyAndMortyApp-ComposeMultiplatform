package presentation.setting.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingModelPresentation(
  val imageVector: ImageVector,
  val title:String,
  val isDarkMode: Boolean,
  val isNavigate: Boolean,
  val navigate: () -> Unit
)
