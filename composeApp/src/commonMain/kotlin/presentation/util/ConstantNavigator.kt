package presentation.util

object ConstantNavigator{
  const val SPLASH_SCREEN = "splash"
  const val HOME_SCREEN = "home_screen"
  const val ABOUT_SCREEN = "about"
  const val FAVORITE_SCREEN = "favorite"
  const val EPISODE_DETAIL_SCREEN = "episode_detail/{episodeId}"
  const val CHARACTER_DETAIL_SCREEN = "character_detail/{characterId}"
  const val LOCATION_DETAIL_SCREEN = "location_detail/{locationId}"

  const val EPISODE_DETAIL_ARGUMENT_KEY = "episodeId"
  const val CHARACTER_DETAIL_ARGUMENT_KEY = "characterId"
  const val LOCATION_DETAIL_ARGUMENT_KEY = "locationId"
}