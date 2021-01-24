package com.example.cache.di

import android.content.Context
import com.example.cache.room.UserDao
import com.example.cache.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {


    @[Provides Singleton]
    fun providesDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.build(context)
    }

    @[Provides Singleton]
    fun providesUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }
}