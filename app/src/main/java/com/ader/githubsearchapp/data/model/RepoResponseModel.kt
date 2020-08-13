package com.ader.githubsearchapp.data.model

import com.google.gson.annotations.SerializedName

data class RepoResponseModel(
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val starsCount: Int
)