package com.example.movieapp.core.domain.repository

import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.Genre
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // Remote operations
    suspend fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>>

    suspend fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>

    suspend fun searchMovies(query: String, page: Int): Flow<Resource<List<Movie>>>

    suspend fun getGenres(): Flow<Resource<List<Genre>>>

    suspend fun getSimilarMovies(movieId: Int, page: Int): Flow<Resource<List<Movie>>>

    // Local operations
    fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun addMovieToFavorites(movieDetail: MovieDetail)

    suspend fun removeMovieFromFavorites(movieId: Int)

    fun isMovieFavorite(movieId: Int): Flow<Boolean>
}