package data.local.episode

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.local.episode.model.EpisodeModel

@Dao
interface EpisodeDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertEpisodeFavoriteRoom(episodeFavoriteRoom: EpisodeModel)

  @Query("SELECT * FROM episode_favorite")
  fun getAllEpisodeFavoriteRoom(): List<EpisodeModel>
}