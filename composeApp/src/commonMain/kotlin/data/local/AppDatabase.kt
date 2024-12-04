package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.episode.EpisodeDao
import data.local.episode.model.EpisodeModel

@Database(
  entities = [EpisodeModel::class],
  version = 1
)
abstract class AppDatabase: RoomDatabase() {
  abstract val episodeDao: EpisodeDao

  companion object {
    const val DB_NAME = "RickAndMortyDB"
  }
}