package com.example.movieapp.features.details.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.core.domain.model.MovieDetail
import com.example.movieapp.core.ui.components.MovieBackdrop
import com.example.movieapp.core.ui.components.MovieRating

@Composable
fun MovieDetailHeader(
    movie: MovieDetail,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MovieBackdrop(
            backdropPath = movie.backdropPath,
            contentDescription = movie.title
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            MovieRating(rating = movie.voteAverage)
        }
    }
}