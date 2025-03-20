package com.example.movieapp.navigation

sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Search : Route("search")
    data object Favorites : Route("favorites")
    data object Details : Route("movie/{movieId}") {
        fun createPath(movieId: Int) = "movie/$movieId"
    }
}