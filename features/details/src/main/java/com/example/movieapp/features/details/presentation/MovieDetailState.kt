package com.example.movieapp.features.details.presentation

import com.example.movieapp.core.common.base.BaseUiState
import com.example.movieapp.core.domain.model.MovieDetail

data class MovieDetailState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val movie: MovieDetail? = null,
    val isFavorite: Boolean = false
) : BaseUiState