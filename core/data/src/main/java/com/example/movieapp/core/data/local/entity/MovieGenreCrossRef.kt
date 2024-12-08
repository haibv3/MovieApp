package com.example.movieapp.core.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "movie_genre_cross_ref",
    primaryKeys = ["movieId", "genreId"]
)
data class MovieGenreCrossRef(
    val movieId: Int,
    val genreId: Int
)