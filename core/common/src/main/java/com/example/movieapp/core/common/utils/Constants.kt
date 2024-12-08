package com.example.movieapp.core.common.utils

object Constants {
    // Api config
    const val TMDB_BASE_URL = MovieAppConfig.TMDB_BASE_URL
    const val TMDB_IMAGE_URL = MovieAppConfig.TMDB_IMAGE_URL
    const val TMDB_API_KEY = MovieAppConfig.TMDB_API_KEY

    // Network timeouts
    const val TIMEOUT_CONNECT = 30L
    const val TIMEOUT_READ = 30L
    const val TIMEOUT_WRITE = 30L

    // Image sizes
    const val POSTER_SIZE_W185 = "w185"
    const val POSTER_SIZE_W500 = "w500"
    const val BACKDROP_SIZE_W780 = "w780"
    const val BACKDROP_SIZE_ORIGINAL = "original"

    // Database
    const val DATABASE_NAME = "movie_database"
    const val DATABASE_VERSION = 1
}