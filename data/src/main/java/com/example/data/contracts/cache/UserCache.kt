package com.example.data.contracts.cache

import com.example.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserCache {

    fun getUserList() : Flow<List<UserEntity>>
    suspend fun getAUser(id: String) : UserEntity
    suspend fun saveCacheList(users : List<UserEntity>)
}