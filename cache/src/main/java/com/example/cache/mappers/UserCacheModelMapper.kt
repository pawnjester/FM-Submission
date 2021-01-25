package com.example.cache.mappers

import com.example.cache.mappers.base.CacheModelMapper
import com.example.cache.models.UserCacheModel
import com.example.data.model.UserEntity
import javax.inject.Inject

class UserCacheModelMapper @Inject constructor() : CacheModelMapper<UserCacheModel, UserEntity> {

    override fun mapToModel(entity: UserEntity): UserCacheModel {
        return entity.run {
            UserCacheModel(
                entity.id,
                entity.lastName,
                entity.firstName,
                entity.picture,
                entity.title,
                entity.email
            )
        }
    }

    override fun mapToEntity(model: UserCacheModel): UserEntity {
        return model.run {
            UserEntity(
                model.id,
                model.lastName,
                model.firstName,
                model.picture,
                model.title,
                model.email
            )
        }
    }

}