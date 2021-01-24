package com.example.remote.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val results: List<UserNetworkModel>
)