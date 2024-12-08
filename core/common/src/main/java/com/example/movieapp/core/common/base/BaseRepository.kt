package com.example.movieapp.core.common.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {
    protected fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Flow<NetworkResult<T>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResult.Success(it))
                }
            } else {
                emit(NetworkResult.Error(response.code(), response.message()))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(-1, e.message ?: "Unknown error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}