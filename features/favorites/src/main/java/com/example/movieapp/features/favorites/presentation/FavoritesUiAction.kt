package com.example.movieapp.features.favorites.presentation

import com.example.movieapp.core.common.base.BaseUiAction

sealed interface FavoritesUiAction : BaseUiAction {
    object RefreshFavorites : FavoritesUiAction
}