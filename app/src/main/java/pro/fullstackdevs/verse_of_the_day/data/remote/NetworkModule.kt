package pro.fullstackdevs.verse_of_the_day.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private val logging = HttpLoggingInterceptor().apply {
        // Log request & response lines and their bodies
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val verseApiService: VerseApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://bible-api.com/")
            .client(client)  // ← use the client with logging
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VerseApiService::class.java)
    }
}
