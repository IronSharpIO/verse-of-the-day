package pro.fullstackdevs.verse_of_the_day

import kotlinx.coroutines.flow.Flow

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

    suspend fun getTodayVerse(today: String): Verse? {
        return verseDao.getVerseByDate(today)    }
}
