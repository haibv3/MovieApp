package com.example.movieapp.core.common.extension

fun String.toMovieImageUrl(): String {
    return "https://image.tmdb.org/t/p/w500/$this"
}

fun String.toMovieBackdropUrl(): String {
    return "https://image.tmdb.org/t/p/original/$this"
}