package com.ader.githubsearchapp.data.network

import com.ader.githubsearchapp.data.model.UserDetailsResponseModel
import com.ader.githubsearchapp.data.model.UserResponseModel
import com.ader.githubsearchapp.data.model.UserSearchResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitUserApi {
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Deferred<Response<UserSearchResponseModel>>

    @GET("users/{username}")
    fun getUsersDetailByUsername(@Path("username") username: String): Deferred<Response<UserDetailsResponseModel>>
}