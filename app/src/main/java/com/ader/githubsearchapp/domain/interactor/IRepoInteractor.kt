package com.ader.githubsearchapp.domain.interactor

import com.ader.githubsearchapp.domain.model.RepoModel

interface IRepoInteractor {
    suspend fun getRepoByUserName(
        username: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<RepoModel>
}