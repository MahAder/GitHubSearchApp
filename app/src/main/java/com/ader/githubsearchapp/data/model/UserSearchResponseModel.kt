package com.ader.githubsearchapp.data.model

import com.google.gson.annotations.SerializedName

data class UserSearchResponseModel(
    @SerializedName("items") val userList: List<UserResponseModel>
)