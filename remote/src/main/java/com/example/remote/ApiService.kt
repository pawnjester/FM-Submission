package com.example.remote

import com.example.remote.models.UserNetworkModel
import com.example.remote.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("user?limit=100")
    suspend fun getUsers(): UserResponse

    @GET("user/{id}")
    suspend fun getSingleUser(@Path("id") id: String): UserNetworkModel
}