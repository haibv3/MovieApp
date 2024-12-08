package com.example.movieapp.core.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreListResponse(
    val genres: List<GenreDto>
)