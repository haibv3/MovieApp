package com.example.movieapp.core.domain.model

import java.util.Date

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: Date?,
    val genres: List<Genre>,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: Int,
    val languages: List<String>,
    val productionCompanies: List<String>
)