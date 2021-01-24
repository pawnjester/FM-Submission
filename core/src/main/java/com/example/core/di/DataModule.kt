package com.example.core.di

import com.example.data.impl.UserRepositoryImpl
import com.example.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun providesUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository

}