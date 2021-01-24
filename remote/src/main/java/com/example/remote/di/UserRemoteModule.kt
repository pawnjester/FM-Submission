package com.example.remote.di

import com.example.data.contracts.remote.UserRemote
import com.example.remote.impl.UserRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UserRemoteModule {

    @Binds
    abstract fun providesUserRemote(impl: UserRemoteImpl): UserRemote
}