package com.example.movieapp.core.common.utils

object ImageUtils {
    fun getPosterUrl(posterPath: String?, size: String = Constants.POSTER_SIZE_W500): String {
        if (posterPath.isNullOrEmpty()) return ""
        return "${Constants.TMDB_IMAGE_URL}$size$posterPath"
    }

    fun getBackdropUrl(backdropPath: String?, size: String = Constants.BACKDROP_SIZE_W780): String {
        if (backdropPath.isNullOrEmpty()) return ""
        return "${Constants.TMDB_IMAGE_URL}$size$backdropPath"
    }
}