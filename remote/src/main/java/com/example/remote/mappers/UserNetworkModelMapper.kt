package com.example.remote.mappers

import com.example.data.model.UserEntity
import com.example.remote.mappers.base.RemoteModelMapper
import com.example.remote.models.UserNetworkModel
import javax.inject.Inject

class UserNetworkModelMapper  @Inject constructor(
) : RemoteModelMapper<UserNetworkModel, UserEntity> {

    override fun mapFromModel(model: UserNetworkModel): UserEntity {
        return UserEntity(
            model.id,
            model.lastName,
            model.firstName,
            model.picture,
            model.title,
            model.email
        )
    }
}