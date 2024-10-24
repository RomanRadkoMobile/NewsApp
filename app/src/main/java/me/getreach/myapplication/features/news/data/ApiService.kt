package me.getreach.myapplication.features.news.data

import me.getreach.myapplication.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines/sources")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsSourcesResponse
}