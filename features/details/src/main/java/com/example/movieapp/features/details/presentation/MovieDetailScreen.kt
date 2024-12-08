package com.example.movieapp.features.details.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.core.ui.components.*
import com.example.movieapp.features.details.presentation.components.MovieDetailContent
import com.example.movieapp.features.details.presentation.components.MovieDetailHeader

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.handleAction(MovieDetailAction.LoadMovieDetail(movieId))
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = uiState.movie?.title ?: "",
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> LoadingScreen()
            uiState.error != null -> ErrorScreen(
                message = uiState.error ?: "Unknown error occurred",
                onRetry = { viewModel.handleAction(MovieDetailAction.LoadMovieDetail(movieId)) }
            )
            uiState.movie != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValues)
                ) {
                    MovieDetailHeader(movie = uiState.movie!!)
                    MovieDetailContent(
                        movie = uiState.movie!!,
                        isFavorite = uiState.isFavorite,
                        onFavoriteClick = { 
                            if (uiState.isFavorite) {
                                viewModel.handleAction(MovieDetailAction.RemoveFromFavorites)
                            } else {
                                viewModel.handleAction(MovieDetailAction.AddToFavorites)
                            }
                        }
                    )
                }
            }
        }
    }
}