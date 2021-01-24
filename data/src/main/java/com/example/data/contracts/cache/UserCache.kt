package com.example.data.contracts.cache

import com.example.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserCache {

    fun getUserCacheList() : Flow<List<UserEntity>>
}