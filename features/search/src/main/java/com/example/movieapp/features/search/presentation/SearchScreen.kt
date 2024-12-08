package com.example.movieapp.features.search.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.core.ui.components.*
import com.example.movieapp.features.search.presentation.components.SearchContent

@Composable
fun SearchScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        SearchContent(
            query = uiState.query,
            movies = uiState.movies,
            isLoading = uiState.isLoading,
            error = uiState.error,
            onQueryChange = { query ->
                viewModel.handleAction(SearchUiAction.Search(query))
            },
            onMovieClick = onMovieClick,
            onLoadMore = {
                viewModel.handleAction(SearchUiAction.LoadMore)
            }
        )
    }
}