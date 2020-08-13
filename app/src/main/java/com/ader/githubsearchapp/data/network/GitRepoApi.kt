package com.ader.githubsearchapp.data.network

import com.ader.githubsearchapp.data.model.RepoResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRepoApi {
    @GET("users/{username}/repos")
    fun getReposByUsername(@Path("username") username: String): Deferred<Response<List<RepoResponseModel>>>
}