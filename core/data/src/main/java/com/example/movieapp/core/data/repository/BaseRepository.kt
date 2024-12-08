package com.example.movieapp.core.data.repository

import com.example.movieapp.core.common.base.NetworkResult
import com.example.movieapp.core.common.base.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.FlowCollector

abstract class BaseRepository {
    protected fun <T> networkBoundResource(
        fetchFromLocal: (suspend () -> Flow<T>)? = null,
        shouldFetch: (T?) -> Boolean = { true },
        fetchFromRemote: suspend () -> Flow<NetworkResult<T>>,
        saveRemoteData: (suspend (T) -> Unit)? = null
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading)

        // First fetch from local if available
        if (fetchFromLocal != null) {
            fetchFromLocal().collect { localData ->
                emit(Resource.Success(localData))
                
                // Fetch from remote if needed
                if (shouldFetch(localData)) {
                    fetchFromNetwork(this, fetchFromRemote, saveRemoteData)
                }
            }
        } else {
            fetchFromNetwork(this, fetchFromRemote, saveRemoteData)
        }
    }

    private suspend fun <T> fetchFromNetwork(
        collector: FlowCollector<Resource<T>>,
        fetchFromRemote: suspend () -> Flow<NetworkResult<T>>,
        saveRemoteData: (suspend (T) -> Unit)?
    ) {
        try {
            fetchFromRemote().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        saveRemoteData?.invoke(result.data)
                        collector.emit(Resource.Success(result.data))
                    }
                    is NetworkResult.Error -> {
                        collector.emit(Resource.Error(result.message))
                    }
                    NetworkResult.Loading -> {
                        // Already emitted Loading
                    }
                }
            }
        } catch (e: Exception) {
            collector.emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }
}