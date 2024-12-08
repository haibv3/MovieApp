package com.example.movieapp.core.data.remote.source

import com.example.movieapp.core.common.base.NetworkResult
import com.example.movieapp.core.data.remote.api.TMDBApi
import com.example.movieapp.core.data.remote.dto.GenreListResponse
import com.example.movieapp.core.data.remote.dto.MovieDetailResponse
import com.example.movieapp.core.data.remote.dto.MovieListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: TMDBApi
) : BaseRemoteDataSource() {

    suspend fun getPopularMovies(page: Int): Flow<NetworkResult<MovieListResponse>> = 
        safeApiCall { api.getPopularMovies(page = page) }

    suspend fun getMovieDetail(movieId: Int): Flow<NetworkResult<MovieDetailResponse>> = 
        safeApiCall { api.getMovieDetail(movieId = movieId) }

    suspend fun searchMovies(query: String, page: Int): Flow<NetworkResult<MovieListResponse>> = 
        safeApiCall { api.searchMovies(query = query, page = page) }

    suspend fun getGenres(): Flow<NetworkResult<GenreListResponse>> = 
        safeApiCall { api.getGenres() }

    suspend fun getSimilarMovies(movieId: Int, page: Int): Flow<NetworkResult<MovieListResponse>> = 
        safeApiCall { api.getSimilarMovies(movieId = movieId, page = page) }
}