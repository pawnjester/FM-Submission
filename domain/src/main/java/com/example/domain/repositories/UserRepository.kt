package com.example.domain.repositories

import com.example.domain.model.Result
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserList() : Flow<Result>

    fun getAUser(id: String) : Flow<User>
}