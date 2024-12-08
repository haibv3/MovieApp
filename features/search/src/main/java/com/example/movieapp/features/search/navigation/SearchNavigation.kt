package com.example.movieapp.features.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieapp.features.search.presentation.SearchScreen

fun NavGraphBuilder.searchScreen(
    onMovieClick: (Int) -> Unit
) {
    composable(route = "search") {
        SearchScreen(onMovieClick = onMovieClick)
    }
}

fun NavController.navigateToSearch() {
    navigate("search")
}