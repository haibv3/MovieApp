package com.example.movieapp.core.common.utils

object ErrorUtils {
    fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is java.net.UnknownHostException -> "No internet connection"
            is java.net.SocketTimeoutException -> "Connection timeout"
            is retrofit2.HttpException -> getHttpErrorMessage(throwable.code())
            else -> throwable.message ?: "Unknown error occurred"
        }
    }

    private fun getHttpErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Unauthorized access"
            403 -> "Forbidden access"
            404 -> "Resource not found"
            500 -> "Internal server error"
            else -> "Error code: $code"
        }
    }
}