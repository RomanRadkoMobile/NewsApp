package me.radko.practice.features.news.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.radko.practice.features.news.domain.model.NewsEntity

@Dao
interface NewsSourceDao {
    @Query("SELECT * FROM news_sources")
    suspend fun getAllNewsSources(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsSources(sources: List<NewsEntity>)

    @Query("SELECT * FROM news_sources WHERE id = :id")
    suspend fun getNewsSourceById(id: String): NewsEntity

}