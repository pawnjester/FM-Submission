package com.example.data.mappers

import com.example.data.mappers.base.EntityMapper
import com.example.data.model.UserEntity
import com.example.domain.model.User
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return entity.run {
            User(
                entity.id,
                entity.lastName,
                entity.firstName,
                entity.picture,
                entity.title,
                entity.email
            )
        }
    }

    override fun mapToEntity(domain: User): UserEntity {
        return domain.run {
            UserEntity(
                domain.id,
                domain.lastName,
                domain.firstName,
                domain.picture,
                domain.title,
                domain.email
            )
        }
    }
}