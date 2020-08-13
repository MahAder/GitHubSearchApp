package com.ader.githubsearchapp.data.repository

import com.ader.githubsearchapp.data.model.RepoResponseModel

interface IRepoRepository {
    suspend fun getUserReposByUsername(userName: String, onErrorAction:(exception: java.lang.Exception) -> Unit): List<RepoResponseModel>
}