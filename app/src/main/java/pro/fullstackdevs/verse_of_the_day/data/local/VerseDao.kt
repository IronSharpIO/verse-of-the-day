package pro.fullstackdevs.verse_of_the_day.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VerseDao {

    @Query("SELECT * FROM verses ORDER BY dateShown DESC")
    fun getAll(): Flow<List<Verse>>

    @Query("SELECT * FROM verses WHERE dateShown = :date")
    fun getVersesByDate(date: String): Flow<List<Verse>>

    @Query("SELECT * FROM verses WHERE dateShown = :date LIMIT 1")
    suspend fun getVerseByDate(date: String): Verse?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(verse: Verse)

    @Update
    suspend fun update(verse: Verse)

    @Delete
    suspend fun delete(verse: Verse)
}
