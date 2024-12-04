import androidx.room.RoomDatabaseConstructor
import data.local.AppDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabaseConstructor: RoomDatabaseConstructor<AppDatabase> {
  override fun initialize(): AppDatabase
}