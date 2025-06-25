package pro.fullstackdevs.verse_of_the_day

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VerseViewModel(application: Application) : AndroidViewModel(application) {

    private val verseDao = VerseDatabase.getDatabase(application).verseDao()

    val allVerses = verseDao.getAllVerses()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insertVerse(verse: Verse) {
        viewModelScope.launch(Dispatchers.IO) {
            verseDao.insertVerse(verse)
        }
    }

    fun markVerseRead(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val today = verseDao.getVerseByDate(date)
            if (today != null && !today.read) {
                verseDao.updateVerse(today.copy(read = true))
            }
        }
    }
}
