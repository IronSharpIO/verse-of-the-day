package pro.fullstackdevs.verse_of_the_day.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "verses")
data class Verse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val reference: String, // e.g., "Matthew 5:5"
    val text: String,      // e.g., "Blessed are the meek: for they shall inherit the earth."
    val dateShown: String, // e.g., "2025-06-25" (we'll use this to track daily verse)
    val read: Boolean = false // whether the user opened it
)
