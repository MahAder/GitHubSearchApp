package com.ader.githubsearchapp.data.repository

import com.ader.githubsearchapp.data.model.UserDetailsResponseModel
import com.ader.githubsearchapp.data.model.UserResponseModel

interface IUserRepository {
    suspend fun searchUsers(
        query: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<UserResponseModel>

    suspend fun getUserDetailsByName(
        name: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): UserDetailsResponseModel?
}