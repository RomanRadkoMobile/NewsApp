package me.radko.practice.features.news.domain.repository

import jakarta.inject.Inject
import me.radko.practice.features.news.data.ApiService
import me.radko.practice.features.news.domain.model.NewsEntity
import me.radko.practice.features.news.data.NewsSourceDao

class NewsDetailsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsSourceDao: NewsSourceDao
) {
    suspend fun getNewsDetails(id: String): NewsEntity {
        return newsSourceDao.getNewsSourceById(id)
    }
}