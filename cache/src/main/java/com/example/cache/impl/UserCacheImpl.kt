package com.example.cache.impl

import com.example.cache.mappers.UserCacheModelMapper
import com.example.cache.room.UserDao
import com.example.data.contracts.cache.UserCache
import com.example.data.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCacheImpl @Inject constructor(
    private val dao: UserDao,
    private val mapper: UserCacheModelMapper
) : UserCache {

    override fun getUserList(): Flow<List<UserEntity>> {
        return flow {
            val users = dao.getUsers()
            emit(users.map {
                mapper.mapToEntity(it)
            })
        }
    }

    override suspend fun getAUser(id: String): UserEntity {
        return mapper.mapToEntity(dao.getAUser(id))
    }

    override suspend fun saveCacheList(users: List<UserEntity>) {
        dao.saveUser(mapper.mapToModelList(users))
    }
}