package com.example.domain.repositories

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserList() : Flow<List<User>>

    fun getAUser(id: String) : Flow<User>
}