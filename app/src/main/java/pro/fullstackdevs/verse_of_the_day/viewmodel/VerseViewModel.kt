package pro.fullstackdevs.verse_of_the_day.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pro.fullstackdevs.verse_of_the_day.data.local.AppDatabase
import pro.fullstackdevs.verse_of_the_day.data.local.Verse
import pro.fullstackdevs.verse_of_the_day.data.local.VerseRepository
import java.text.SimpleDateFormat
import java.util.*

class VerseViewModel(application: Application) : AndroidViewModel(application) {

    private val verseDao = AppDatabase.getDatabase(application).verseDao()
    private val repository = VerseRepository(verseDao)
    private val today = getTodayDate()
    val todayVerses: StateFlow<List<Verse>> =
        repository.getVersesForDate(today)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

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

    fun fetchTodayVerseIfNeeded() = viewModelScope.launch {
        val today = getTodayDate()
        if (repository.getTodayVerse(today) == null) {
            try {
                val verse = repository.fetchVerseOfTheDay()
                repository.insert(verse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getTodayDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(Date())
    }
}
