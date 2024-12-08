package com.example.movieapp.core.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    val genres: List<GenreDto>,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    val runtime: Int?,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<LanguageDto>,
    @Json(name = "production_companies")
    val productionCompanies: List<CompanyDto>
)