package com.example.movieapp.core.domain.repository

import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.Genre
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // Remote operations
    fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>>

    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>

    fun searchMovies(query: String, page: Int): Flow<Resource<List<Movie>>>

    fun getGenres(): Flow<Resource<List<Genre>>>

    fun getSimilarMovies(movieId: Int, page: Int): Flow<Resource<List<Movie>>>

    // Local operations
    fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun addMovieToFavorites(movieDetail: MovieDetail)

    suspend fun removeMovieFromFavorites(movieId: Int)

    fun isMovieFavorite(movieId: Int): Flow<Boolean>
}