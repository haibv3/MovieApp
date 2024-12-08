package com.example.movieapp.core.data.remote.mapper

import com.example.movieapp.core.data.local.entity.FavoriteMovieEntity
import com.example.movieapp.core.data.local.entity.GenreEntity
import com.example.movieapp.core.data.remote.dto.GenreDto
import com.example.movieapp.core.data.remote.dto.MovieDetailResponse
import com.example.movieapp.core.data.remote.dto.MovieDto
import com.example.movieapp.core.domain.model.Genre
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.model.MovieDetail
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Extension function to convert MovieDto to domain Movie model
 */
fun MovieDto.toDomainModel() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate?.let { parseDate(it) },
    genreIds = genreIds,
    voteAverage = voteAverage,
    voteCount = voteCount
)

/**
 * Extension function to convert MovieDetailResponse to domain MovieDetail model
 */
fun MovieDetailResponse.toDomainModel() = MovieDetail(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate?.let { parseDate(it) },
    genres = genres.map { it.toDomainModel() },
    voteAverage = voteAverage,
    voteCount = voteCount,
    runtime = runtime ?: 0,
    languages = spokenLanguages.map { it.englishName },
    productionCompanies = productionCompanies.map { it.name }
)

/**
 * Extension function to convert GenreDto to domain Genre model
 */
fun GenreDto.toDomainModel() = Genre(
    id = id,
    name = name
)

/**
 * Extension function to convert MovieDetailResponse to FavoriteMovieEntity
 */
fun MovieDetailResponse.toFavoriteEntity() = FavoriteMovieEntity(
    movieId = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate?.let { parseDate(it) },
    voteAverage = voteAverage,
    voteCount = voteCount
)

/**
 * Extension function to convert MovieDetail to FavoriteMovieEntity
 */
fun MovieDetail.toFavoriteEntity() = FavoriteMovieEntity(
    movieId = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

/**
 * Extension function to convert GenreDto to GenreEntity
 */
fun GenreDto.toEntity() = GenreEntity(
    genreId = id,
    name = name
)

/**
 * Helper function to parse date string from API
 */
private fun parseDate(dateString: String) = try {
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
} catch (e: Exception) {
    null
}