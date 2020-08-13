package com.ader.githubsearchapp.data.repository

import com.ader.githubsearchapp.data.model.RepoResponseModel
import com.ader.githubsearchapp.data.network.GitRepoApi

class RepoRepository(private val gitRepoApi: GitRepoApi) : BaseRepository(), IRepoRepository {
    override suspend fun getUserReposByUsername(
        userName: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<RepoResponseModel> {
        val resp = safeApiCall(
            call = { gitRepoApi.getReposByUsername(userName).await() },
            onErrorAction = onErrorAction
        )

        return resp ?: ArrayList()
    }
}