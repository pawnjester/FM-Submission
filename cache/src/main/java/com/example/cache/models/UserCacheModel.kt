package com.example.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cache.models.UserCacheModel.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class UserCacheModel(
    @PrimaryKey
    val id : String,
    val lastName : String,
    val firstName : String,
    val picture: String,
    val title: String,
    val email: String
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}