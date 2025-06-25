package pro.fullstackdevs.verse_of_the_day

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VerseViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VerseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VerseViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}