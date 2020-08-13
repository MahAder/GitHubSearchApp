package com.ader.githubsearchapp.data.model

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("login") val login: String
)