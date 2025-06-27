package pro.fullstackdevs.verse_of_the_day.data.remote

import com.google.gson.annotations.SerializedName

data class VerseResponse(
    val reference: String,           // e.g. "Song of Solomon 7:3"
    val text: String,                // same as verses[0].text
    val verses: List<VerseDto>,      // you can ignore this if you only need text+reference
    @SerializedName("translation_id")
    val translationId: String,
    @SerializedName("translation_name")
    val translationName: String
)

data class VerseDto(
    @SerializedName("book_id")   val bookId: String,
    @SerializedName("book_name") val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String
)