package com.example.data.contracts.remote

import com.example.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRemote {

    fun getUserList() : Flow<List<UserEntity>>

    fun getAUser(id: String) : Flow<UserEntity>
}