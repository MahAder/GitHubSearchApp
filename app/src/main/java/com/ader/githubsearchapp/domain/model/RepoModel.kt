package com.ader.githubsearchapp.domain.model

import com.ader.githubsearchapp.presentation.model.UIRepoModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoModel(
    val name: String,
    val htmlUrl: String,
    val forksCount: Int,
    val starsCount: Int
): Serializable, UIRepoModel{
    override fun getRepoName(): String {
        return name
    }

    override fun getRepoHtmlUrl(): String {
        return htmlUrl
    }

    override fun getRepoForksCount(): Int {
        return forksCount
    }

    override fun getRepoStarsCount(): Int {
        return starsCount
    }

}