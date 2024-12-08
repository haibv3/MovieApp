package com.example.movieapp.features.search.presentation

import com.example.movieapp.core.common.base.BaseUiState
import com.example.movieapp.core.domain.model.Movie

data class SearchUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val query: String = "",
    val movies: List<Movie> = emptyList()
) : BaseUiState