// MovieBackdrop.kt
package com.example.movieapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.movieapp.core.common.utils.Constants

@Composable
fun MovieBackdrop(
    backdropPath: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f/9f)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        if (!backdropPath.isNullOrEmpty()) {
            AsyncImage(
                model = "${Constants.TMDB_IMAGE_URL}${Constants.BACKDROP_SIZE_W780}$backdropPath",
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}