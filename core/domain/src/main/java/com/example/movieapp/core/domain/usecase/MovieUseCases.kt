package com.example.movieapp.core.domain.usecase

import javax.inject.Inject

data class MovieUseCases @Inject constructor(
    val getPopularMovies: GetPopularMoviesUseCase,
    val getMovieDetail: GetMovieDetailUseCase,
    val searchMovies: SearchMoviesUseCase,
    val getGenres: GetGenresUseCase,
    val getSimilarMovies: GetSimilarMoviesUseCase,
    val getFavoriteMovies: GetFavoriteMoviesUseCase,
    val addMovieToFavorites: AddMovieToFavoritesUseCase,
    val removeMovieFromFavorites: RemoveMovieFromFavoritesUseCase,
    val isMovieFavorite: IsMovieFavoriteUseCase
)