package pro.fullstackdevs.verse_of_the_day.data.local

import android.util.Log
import kotlinx.coroutines.flow.Flow
import pro.fullstackdevs.verse_of_the_day.data.remote.NetworkModule
import pro.fullstackdevs.verse_of_the_day.utils.RandomVerseGenerator
import java.text.SimpleDateFormat
import java.util.*

class VerseRepository(private val verseDao: VerseDao) {


    val allVerses: Flow<List<Verse>> = verseDao.getAll()

    suspend fun insert(verse: Verse) {
        verseDao.insert(verse)
    }

    suspend fun update(verse: Verse) {
        verseDao.update(verse)
    }

    suspend fun delete(verse: Verse) {
        verseDao.delete(verse)
    }

    suspend fun getTodayVerse(date: String): Verse? {
        return verseDao.getVerseByDate(date)
    }

    fun getVersesForDate(date: String): Flow<List<Verse>> =
        verseDao.getVersesByDate(date)

    // Fetch from API and return a verse object
    suspend fun fetchVerseOfTheDay(): Verse {
        val rawRef     = RandomVerseGenerator.getRandomVerseReference()
        val encodedRef = rawRef.replace(" ", "%20")     // e.g. "Song%20of%20Solomon%207:3"

        val apiResp = NetworkModule
            .verseApiService
            .getVerse(encodedRef)

        return Verse(
            reference = apiResp.reference,
            text      = apiResp.text,
            dateShown = getTodayDate(),
            read      = false
        )
    }


    // Help[er function for date format
    private fun getTodayDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(Date())
    }
}
