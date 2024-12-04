import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Builder
import data.local.AppDatabase

actual class DatabaseFactory(
  private val context: Context
) {
  actual fun create(): Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(AppDatabase.DB_NAME)

    return Room.databaseBuilder(
      context = context,
      name = dbFile.absolutePath
    )
  }
}