package me.getreach.myapplication.features.news.data

import kotlinx.serialization.Serializable
import me.getreach.myapplication.features.news.domain.model.NewsEntity

@Serializable
data class NewsSourcesResponse(
    val status: String,
    val sources: List<NewsSource>
)

@Serializable
data class NewsSource(
    val id: String?,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) {
    fun toEntity(): NewsEntity {
        return NewsEntity(
            id = id ?: "",
            name = name,
            description = description,
            url = url
        )
    }
}