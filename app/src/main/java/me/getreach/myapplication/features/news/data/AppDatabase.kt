package me.getreach.myapplication.features.news.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.getreach.myapplication.features.news.domain.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsSourceDao(): NewsSourceDao
}