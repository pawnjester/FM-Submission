package com.example.data.impl

import com.example.data.contracts.cache.UserCache
import com.example.data.contracts.remote.UserRemote
import com.example.data.mappers.UserEntityMapper
import com.example.domain.model.Result
import com.example.domain.model.User
import com.example.domain.repositories.UserRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val mapper: UserEntityMapper,
    private val userRemote: UserRemote,
    private val userCache: UserCache
) : UserRepository {


    override fun getUserList(): Flow<Result> {
        return flow {
            getUsersAndCache()
            emitAll(
                userCache.getUserList().map { usersList ->
                    Result(mapper.mapFromEntityList(usersList))
                }
            )
        }.catch { error ->
            val oldData = userCache.getUserList().first()
            emit(Result(mapper.mapFromEntityList(oldData), error))
        }
    }

    override fun getAUser(id: String): Flow<User> {
        return flow {
            emitAll(userRemote.getAUser(id).map {
                mapper.mapFromEntity(it)
            })
        }.catch {
            val oldData = userCache.getAUser(id)
            emit(mapper.mapFromEntity(oldData))
        }
    }

    private suspend fun getUsersAndCache() {
        val users = userRemote.getUserList()
        if (users.isNotEmpty()) {
            userCache.saveCacheList(users)
        }
    }

}