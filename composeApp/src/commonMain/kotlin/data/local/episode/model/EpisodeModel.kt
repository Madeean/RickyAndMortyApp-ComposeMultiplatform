package data.local.episode.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_favorite")
data class EpisodeModel (
  @PrimaryKey @ColumnInfo(name = "id_episode") val idEpisode:Int,
)

