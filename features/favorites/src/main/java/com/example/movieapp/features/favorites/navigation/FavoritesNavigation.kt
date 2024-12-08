package com.example.movieapp.features.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieapp.features.favorites.presentation.FavoritesScreen

fun NavGraphBuilder.favoritesScreen(
    onMovieClick: (Int) -> Unit
) {
    composable(route = "favorites") {
        FavoritesScreen(onMovieClick = onMovieClick)
    }
}

fun NavController.navigateToFavorites() {
    navigate("favorites")
}