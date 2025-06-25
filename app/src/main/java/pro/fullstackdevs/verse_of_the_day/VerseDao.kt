package pro.fullstackdevs.verse_of_the_day

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VerseDao {

    @Query("SELECT * FROM verses ORDER BY dateShown DESC")
    fun getAllVerses(): Flow<List<Verse>>

    @Query("SELECT * FROM verses WHERE dateShown = :date LIMIT 1")
    suspend fun getVerseByDate(date: String): Verse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerse(verse: Verse)

    @Update
    suspend fun updateVerse(verse: Verse)

    @Delete
    suspend fun deleteVerse(verse: Verse)

}