package com.example.movieapp.core.data.remote.api

import com.example.movieapp.core.data.remote.dto.GenreListResponse
import com.example.movieapp.core.data.remote.dto.MovieDetailResponse
import com.example.movieapp.core.data.remote.dto.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET(ApiConstants.Endpoints.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(ApiConstants.Params.PAGE) page: Int = ApiConstants.DEFAULT_PAGE,
        @Query(ApiConstants.Params.LANGUAGE) language: String = ApiConstants.DEFAULT_LANGUAGE
    ): Response<MovieListResponse>

    @GET(ApiConstants.Endpoints.MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path(ApiConstants.Params.MOVIE_ID) movieId: Int,
        @Query(ApiConstants.Params.LANGUAGE) language: String = ApiConstants.DEFAULT_LANGUAGE
    ): Response<MovieDetailResponse>

    @GET(ApiConstants.Endpoints.SEARCH_MOVIES)
    suspend fun searchMovies(
        @Query(ApiConstants.Params.QUERY) query: String,
        @Query(ApiConstants.Params.PAGE) page: Int = ApiConstants.DEFAULT_PAGE,
        @Query(ApiConstants.Params.LANGUAGE) language: String = ApiConstants.DEFAULT_LANGUAGE
    ): Response<MovieListResponse>

    @GET(ApiConstants.Endpoints.GENRES)
    suspend fun getGenres(
        @Query(ApiConstants.Params.LANGUAGE) language: String = ApiConstants.DEFAULT_LANGUAGE
    ): Response<GenreListResponse>

    @GET(ApiConstants.Endpoints.SIMILAR_MOVIES)
    suspend fun getSimilarMovies(
        @Path(ApiConstants.Params.MOVIE_ID) movieId: Int,
        @Query(ApiConstants.Params.PAGE) page: Int = ApiConstants.DEFAULT_PAGE,
        @Query(ApiConstants.Params.LANGUAGE) language: String = ApiConstants.DEFAULT_LANGUAGE
    ): Response<MovieListResponse>
}