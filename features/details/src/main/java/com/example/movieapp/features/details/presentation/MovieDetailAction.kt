package com.example.movieapp.features.details.presentation

import com.example.movieapp.core.common.base.BaseUiAction

sealed interface MovieDetailAction : BaseUiAction {
    data class LoadMovieDetail(val movieId: Int) : MovieDetailAction
    object AddToFavorites : MovieDetailAction
    object RemoveFromFavorites : MovieDetailAction
}