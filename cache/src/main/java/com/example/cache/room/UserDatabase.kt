package com.example.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.BuildConfig
import com.example.cache.models.UserCacheModel


@Database(
    entities = [
        UserCacheModel::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "recipe_db"

        fun build(context: Context): UserDatabase = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}