package com.example.movieapp.core.common.base

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(message: String) = Error(message)
        fun loading() = Loading
    }
}