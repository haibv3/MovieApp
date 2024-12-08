package com.example.movieapp.navigation

sealed class Route(val path: String) {
    object Home : Route("home")
    object Search : Route("search")  
    object Favorites : Route("favorites")
    object Details : Route("movie/{movieId}") {
        fun createPath(movieId: Int) = "movie/$movieId"
    }
}