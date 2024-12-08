package com.example.movieapp.core.data.remote.api

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data object Loading : Result<Nothing>()
    
    companion object {
        suspend fun <T> safeApiCall(
            apiCall: suspend () -> retrofit2.Response<T>
        ): Result<T> {
            return try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Success(body)
                    } else {
                        Error(Exception("Response body is null"))
                    }
                } else {
                    Error(Exception("API call failed with code: ${response.code()}"))
                }
            } catch (e: Exception) {
                Error(e)
            }
        }
    }
}