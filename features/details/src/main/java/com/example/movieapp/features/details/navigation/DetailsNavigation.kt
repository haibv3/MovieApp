package com.example.movieapp.features.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.features.details.presentation.MovieDetailScreen

fun NavGraphBuilder.detailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "movie/{movieId}",
        arguments = listOf(
            navArgument("movieId") { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
        MovieDetailScreen(
            movieId = movieId,
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToMovieDetail(movieId: Int) {
    navigate("movie/$movieId")
}