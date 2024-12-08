package com.example.movieapp.features.home.presentation

import com.example.movieapp.core.common.base.BaseUiAction

sealed interface HomeUiAction : BaseUiAction {
    data object LoadMoreMovies : HomeUiAction
    data object RefreshMovies : HomeUiAction
}