package com.example.movieapp.features.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.ui.components.GridLayout
import com.example.movieapp.core.ui.components.LoadMoreHandler
import com.example.movieapp.core.ui.components.MovieGridItem

@Composable
fun MovieGrid(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    val gridState = rememberLazyGridState()
    
    GridLayout(
        modifier = modifier,
        state = gridState
    ) {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            MovieGridItem(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )
        }

        if (isLoading && movies.isNotEmpty()) {
            item(span = { GridItemSpan(2) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    LoadMoreHandler(
        listState = gridState,
        onLoadMore = onLoadMore
    )
}