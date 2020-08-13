package com.ader.githubsearchapp.domain.interactor

import com.ader.githubsearchapp.domain.model.UserModel

interface IUserIntreactor {
    suspend fun searchUsers(
        query: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<UserModel>
}