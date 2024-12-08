package com.example.movieapp.features.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieapp.features.home.presentation.HomeScreen

fun NavGraphBuilder.homeScreen(
    onMovieClick: (Int) -> Unit
) {
    composable(route = "home") {
        HomeScreen(
            onMovieClick = onMovieClick
        )
    }
}

fun NavController.navigateToHome() {
    navigate("home") {
        popUpTo("home") { inclusive = true }
    }
}