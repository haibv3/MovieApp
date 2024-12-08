package com.example.movieapp.features.favorites.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.core.ui.components.*
import com.example.movieapp.features.favorites.presentation.components.EmptyFavorites
import com.example.movieapp.features.favorites.presentation.components.FavoritesGrid

@Composable
fun FavoritesScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(title = "Favorites")
        }
    ) { paddingValues ->
        when {
            uiState.movies.isEmpty() -> EmptyFavorites(
                modifier = Modifier.padding(paddingValues)
            )
            else -> FavoritesGrid(
                movies = uiState.movies,
                onMovieClick = onMovieClick,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}