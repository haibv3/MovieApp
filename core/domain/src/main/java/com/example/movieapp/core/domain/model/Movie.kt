package com.example.movieapp.core.domain.model

import java.util.Date

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: Date?,
    val genreIds: List<Int>,
    val voteAverage: Double,
    val voteCount: Int
)