package com.example.ui.viewModel

import com.example.domain.model.User
import com.example.ui.model.UserModel
import java.util.*

object DataFactory {

    fun makeUserEntity(): User {
        return User(makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString())
    }

    fun makeUserListEntity(count: Int) : List<User> {
        val list = mutableListOf<User>()
        repeat(count) {
            list.add(makeUserEntity())
        }
        return list
    }


    fun makeUserModel(): UserModel {
        return UserModel(makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString(),
            makeRandomString())
    }

    fun makeUserListModel(count: Int) : List<UserModel> {
        val list = mutableListOf<UserModel>()
        repeat(count) {
            list.add(makeUserModel())
        }
        return list
    }

    fun makeRandomString(): String = UUID.randomUUID().toString()
}