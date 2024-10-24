package me.radko.practice.features.news.domain.repository

import jakarta.inject.Inject
import me.radko.practice.features.news.data.ApiService
import me.radko.practice.features.news.domain.model.NewsEntity
import me.radko.practice.features.news.data.NewsSourceDao
import timber.log.Timber

class NewsListRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsSourceDao: NewsSourceDao
) {

    companion object {
        private const val STATUS_OK = "ok"
    }

    suspend fun getNewsSources(country: String): List<NewsEntity> {
        // Fetch data from local database
        val localData = newsSourceDao.getAllNewsSources()
        return localData.ifEmpty {
            // Fetch data from network if local data is empty
            try {
                val networkResponse = apiService.getTopHeadlines(country)
                Timber.d("Network response: $networkResponse")
                if (networkResponse.status == STATUS_OK) {
                    // Map and save network data to the local database
                    val networkData = networkResponse.sources.map { it.toEntity() } ?: emptyList()
                    if (networkData.isNotEmpty()) {
                        newsSourceDao.insertNewsSources(networkData)
                    }
                    // Return the fetched network data
                    networkData
                } else {
                    Timber.e("Network error: ${networkResponse.status}")
                    emptyList()
                }
            } catch (e: Exception) {
                Timber.e("Exception during network call: $e")
                emptyList() // Return an empty list if there's an exception
            }
        }
    }
}