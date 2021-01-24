package com.example.remote.impl

import com.example.data.contracts.remote.UserRemote
import com.example.data.model.UserEntity
import com.example.remote.ApiService
import com.example.remote.mappers.UserNetworkModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRemoteImpl @Inject constructor(
    private val mapper: UserNetworkModelMapper,
    private val apiService: ApiService
) : UserRemote {

    override fun getUserList(): Flow<List<UserEntity>> {
        return flow {
            val apiResponse = apiService.getUsers().results
            if (apiResponse.isNotEmpty()) {
                emit(apiResponse.map {
                    mapper.mapFromModel(it)
                })
            }

        }
    }

    override fun getAUser(id: String): Flow<UserEntity> {
        return flow {
            val apiResponse = apiService.getSingleUser(id)
            emit(mapper.mapFromModel(apiResponse))
        }
    }
}