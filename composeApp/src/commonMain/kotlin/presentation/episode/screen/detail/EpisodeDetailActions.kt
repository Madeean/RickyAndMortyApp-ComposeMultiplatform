package presentation.episode.screen.detail

sealed interface EpisodeDetailActions {
  data class OnFavoriteClick(val idEpisode: Int): EpisodeDetailActions
}