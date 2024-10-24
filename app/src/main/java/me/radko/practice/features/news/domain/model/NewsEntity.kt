package me.radko.practice.features.news.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_sources")
data class NewsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val url: String
)