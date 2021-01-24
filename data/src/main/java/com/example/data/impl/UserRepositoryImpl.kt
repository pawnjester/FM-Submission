package com.example.data.impl

import com.example.data.contracts.cache.UserCache
import com.example.data.contracts.remote.UserRemote
import com.example.data.mappers.UserEntityMapper
import com.example.domain.model.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val mapper: UserEntityMapper,
    private val userRemote: UserRemote,
    private val userCache: UserCache
) : UserRepository {


    override fun getUserList(): Flow<List<User>> {
        return flow {
            emitAll(userRemote.getUserList().map { usersList ->
//                val users = if (usersList.isEmpty()) {
//                    userCache.getUserCacheList()
//                } else {
                    mapper.mapFromEntityList(usersList)

//                }
            })
        }
    }

    override fun getAUser(id: String): Flow<User> {
        return flow {
            emitAll(userRemote.getAUser(id).map {
                mapper.mapFromEntity(it)
            })
        }
    }

}