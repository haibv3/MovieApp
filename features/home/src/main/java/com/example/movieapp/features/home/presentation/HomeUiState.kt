package com.example.movieapp.features.home.presentation

import com.example.movieapp.core.common.base.BaseUiState
import com.example.movieapp.core.domain.model.Genre
import com.example.movieapp.core.domain.model.Movie

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val movies: List<Movie> = emptyList(),
    val genres: List<Genre> = emptyList()
) : BaseUiState