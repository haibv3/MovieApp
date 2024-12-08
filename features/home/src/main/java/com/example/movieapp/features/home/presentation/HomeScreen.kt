package com.example.movieapp.features.home.presentation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.core.ui.base.BaseScreen
import com.example.movieapp.features.home.presentation.components.HomeTopBar
import com.example.movieapp.features.home.presentation.components.MovieGrid

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    BaseScreen(
        topBar = { HomeTopBar() },
        isLoading = uiState.isLoading,
        error = uiState.error,
        hasData = uiState.movies.isNotEmpty(),
        onRetry = { viewModel.handleAction(HomeUiAction.RefreshMovies) }
    ) { paddingValues ->
        MovieGrid(
            movies = uiState.movies,
            onMovieClick = onMovieClick,
            onLoadMore = { viewModel.handleAction(HomeUiAction.LoadMoreMovies) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}