package pro.fullstackdevs.verse_of_the_day

import android.content.Context
import androidx.compose.material3.rememberTimePickerState
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Verse::class], version = 1, exportSchema = false)
abstract class VerseDatabase : RoomDatabase() {

    abstract fun verseDao(): VerseDao

    companion object {
        @Volatile
        private var INSTANCE: VerseDatabase? = null

        fun getDatabase(context: Context): VerseDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VerseDatabase::class.java,
                    "verse_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}