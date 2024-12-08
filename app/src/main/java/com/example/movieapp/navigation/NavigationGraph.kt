package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieapp.features.details.navigation.detailsScreen
import com.example.movieapp.features.details.navigation.navigateToMovieDetail
import com.example.movieapp.features.favorites.navigation.favoritesScreen
import com.example.movieapp.features.home.navigation.homeScreen
import com.example.movieapp.features.search.navigation.searchScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.path,
        modifier = modifier
    ) {
        homeScreen(
            onMovieClick = { movieId ->
                navController.navigateToMovieDetail(movieId)
            }
        )
        searchScreen(
            onMovieClick = { movieId ->
                navController.navigateToMovieDetail(movieId)
            }
        )
        favoritesScreen(
            onMovieClick = { movieId ->
                navController.navigateToMovieDetail(movieId)
            }
        )
        detailsScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}