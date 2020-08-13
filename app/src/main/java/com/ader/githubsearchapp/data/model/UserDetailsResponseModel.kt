package com.ader.githubsearchapp.data.model

import com.google.gson.annotations.SerializedName

class UserDetailsResponseModel(
    @SerializedName("bio") val bio: String?,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("email") val email: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("created_at") val joinDate: String,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("following") val followingCount: Int,
    @SerializedName("public_repos") val repositoriesCount: Int
)