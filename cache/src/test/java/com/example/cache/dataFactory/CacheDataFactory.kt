package com.example.cache.dataFactory

import com.example.cache.models.UserCacheModel
import com.example.data.model.UserEntity
import java.util.*

object CacheDataFactory {

    fun makeUserCacheModel(): UserCacheModel {
        return UserCacheModel(makeRandomString(), makeRandomString(), makeRandomString(), makeRandomString(), makeRandomString(), makeRandomString())
    }

    fun makeUserCacheModelList(count: Int): List<UserCacheModel> {
        val list = mutableListOf<UserCacheModel>()
        repeat(count) {
            list.add(makeUserCacheModel())
        }
        return list
    }

    fun makeUserCacheEntity(): UserEntity {
        return UserEntity(makeRandomString(), makeRandomString(), makeRandomString(), makeRandomString(), makeRandomString(), makeRandomString())
    }

    fun makeUserCacheEntityList(count: Int): List<UserEntity> {
        val list = mutableListOf<UserEntity>()
        repeat(count) {
            list.add(makeUserCacheEntity())
        }
        return list
    }

    private fun makeRandomString(): String = UUID.randomUUID().toString()
}