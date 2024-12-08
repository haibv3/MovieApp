package com.example.movieapp.core.data.remote.source

import com.example.movieapp.core.common.base.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRemoteDataSource {
    protected fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<NetworkResult<T>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = apiCall()
            emit(
                when {
                    response.isSuccessful -> {
                        response.body()?.let { NetworkResult.Success(it) }
                            ?: NetworkResult.Error(response.code(), "Empty response body")
                    }
                    else -> NetworkResult.Error(response.code(), response.message())
                }
            )
        } catch (e: Exception) {
            emit(NetworkResult.Error(-1, e.message ?: "Unknown error occurred"))
        }
    }
}