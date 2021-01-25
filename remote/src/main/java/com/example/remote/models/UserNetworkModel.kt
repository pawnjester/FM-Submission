package com.example.remote.models

import com.google.gson.annotations.SerializedName

data class UserNetworkModel(
    val id: String,
    @SerializedName("lastName")
    val lastName : String,
    @SerializedName("firstName")
    val firstName : String,
    @SerializedName("picture")
    val picture : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("email")
    val email : String
)