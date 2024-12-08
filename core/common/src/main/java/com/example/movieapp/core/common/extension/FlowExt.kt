package com.example.movieapp.core.common.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import com.example.movieapp.core.common.base.Resource

fun <T> Flow<T>.asResource(): Flow<Resource<T>> = this
    .map<T, Resource<T>> { Resource.success(it) }
    .onStart { emit(Resource.loading()) }
    .catch { emit(Resource.error(it.message ?: "Unknown error occurred")) }