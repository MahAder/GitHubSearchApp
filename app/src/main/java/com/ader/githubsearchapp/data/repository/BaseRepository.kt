package com.ader.githubsearchapp.data.repository

import retrofit2.Response
import java.io.IOException


sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, onErrorAction:(exception: java.lang.Exception) -> Unit): T? {

        val result : Result<T> = safeApiResult(call)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                onErrorAction(result.exception)
            }
        }

        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>) : Result<T>{
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException(response.message()))
    }
}