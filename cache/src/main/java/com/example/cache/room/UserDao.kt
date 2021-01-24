package com.example.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.models.UserCacheModel
import com.example.cache.models.UserCacheModel.Companion.TABLE_NAME

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserCacheModel)

    @Query("select * from $TABLE_NAME")
    suspend fun getUsers(): List<UserCacheModel>

}