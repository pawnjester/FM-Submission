package com.example.data.contracts.remote

import com.example.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRemote {

    suspend fun getUserList() : List<UserEntity>

    fun getAUser(id: String) : Flow<UserEntity>
}