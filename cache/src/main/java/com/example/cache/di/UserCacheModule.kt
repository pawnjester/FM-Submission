package com.example.cache.di

import com.example.cache.impl.UserCacheImpl
import com.example.data.contracts.cache.UserCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UserCacheModule {

    @Binds
    abstract fun providesRecipeCache(recipeCacheImpl: UserCacheImpl): UserCache
}