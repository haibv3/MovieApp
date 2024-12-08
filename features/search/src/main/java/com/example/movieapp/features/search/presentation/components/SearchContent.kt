package com.example.movieapp.features.search.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.ui.components.*
import com.example.movieapp.core.ui.components.MovieGridItem

@Composable
fun SearchContent(
    query: String,
    movies: List<Movie>,
    isLoading: Boolean,
    error: String?,
    onQueryChange: (String) -> Unit,
    onMovieClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onClearQuery = { onQueryChange("") }
        )
        
        when {
            isLoading && movies.isEmpty() -> LoadingScreen()
            error != null && movies.isEmpty() -> ErrorScreen(
                message = error,
                onRetry = { onQueryChange(query) }
            )
            movies.isEmpty() && query.isNotBlank() -> EmptyResults()
            else -> SearchResults(
                movies = movies,
                onMovieClick = onMovieClick,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun EmptyResults() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text("No results found")
    }
}

@Composable
private fun SearchResults(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onLoadMore: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MovieGridItem(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )
        }

        item(span = { GridItemSpan(2) }) {
            if (movies.isNotEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentWidth()
                )
                androidx.compose.runtime.LaunchedEffect(true) {
                    onLoadMore()
                }
            }
        }
    }
}