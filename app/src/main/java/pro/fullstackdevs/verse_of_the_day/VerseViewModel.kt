package pro.fullstackdevs.verse_of_the_day

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VerseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: VerseRepository

    val allVerses: Flow<List<Verse>>

    init {
        val verseDao = AppDatabase.getDatabase(application).verseDao()
        repository = VerseRepository(verseDao)
        allVerses = repository.allVerses
    }

    fun insertVerse(verse: Verse) {
        viewModelScope.launch {
            repository.insert(verse)
        }
    }

    fun updateVerse(verse: Verse) {
        viewModelScope.launch {
            repository.update(verse)
        }
    }
}
