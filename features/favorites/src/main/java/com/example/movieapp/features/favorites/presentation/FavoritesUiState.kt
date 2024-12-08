package com.example.movieapp.features.favorites.presentation

import com.example.movieapp.core.common.base.BaseUiState
import com.example.movieapp.core.domain.model.Movie

data class FavoritesUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val movies: List<Movie> = emptyList()
) : BaseUiState