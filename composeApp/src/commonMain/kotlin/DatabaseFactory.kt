import androidx.room.RoomDatabase
import data.local.AppDatabase

expect class DatabaseFactory {
  fun create(): RoomDatabase.Builder<AppDatabase>
}