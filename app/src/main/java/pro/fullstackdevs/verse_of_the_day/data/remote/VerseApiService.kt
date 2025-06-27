package pro.fullstackdevs.verse_of_the_day.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VerseApiService {
    @GET("{verse}")
    suspend fun getVerse(
        @Path("verse", encoded = true) verse: String,
        @Query("translation") translation: String = "kjv"
    ): VerseResponse
}
