package com.example.ui.mapper

import com.example.domain.model.User
import com.example.ui.mapper.base.ModelMapper
import com.example.ui.model.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor(
) : ModelMapper<UserModel, User> {

    override fun mapToModel(domain: User): UserModel {
        return domain.run {
            UserModel(
                domain.id,
                domain.lastName,
                domain.firstName,
                domain.picture,
                domain.title
            )
        }
    }

    override fun mapToDomain(model: UserModel): User {
        return model.run {
            User(
                model.id,
                model.lastName,
                model.firstName,
                model.picture,
                model.title
            )
        }
    }
}