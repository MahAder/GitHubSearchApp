package com.ader.githubsearchapp.data.repository

import com.ader.githubsearchapp.data.model.UserDetailsResponseModel
import com.ader.githubsearchapp.data.model.UserResponseModel
import com.ader.githubsearchapp.data.network.GitUserApi

class UserRepository(private val gitApi: GitUserApi) : BaseRepository(), IUserRepository {
    override suspend fun searchUsers(
        query: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<UserResponseModel> {
        val resp = safeApiCall(call = { gitApi.searchUsers(query).await()},
        onErrorAction = onErrorAction)

        return resp?.userList ?: ArrayList()
    }

    override suspend fun getUserDetailsByName(
        name: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): UserDetailsResponseModel? {
        val resp = safeApiCall(call = { gitApi.getUsersDetailByUsername(name).await()},
        onErrorAction = onErrorAction)

        return resp
    }
}