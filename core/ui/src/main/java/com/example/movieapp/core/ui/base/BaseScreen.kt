package com.example.movieapp.core.ui.base

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieapp.core.ui.components.ErrorScreen
import com.example.movieapp.core.ui.components.LoadingScreen

@Composable
fun BaseScreen(
    topBar: @Composable () -> Unit = {},
    isLoading: Boolean = false,
    error: String? = null,
    onRetry: () -> Unit = {},
    hasData: Boolean = false,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = topBar
    ) { paddingValues ->
        when {
            isLoading && !hasData -> LoadingScreen()
            error != null && !hasData -> {
                ErrorScreen(
                    message = error,
                    onRetry = onRetry
                )
            }
            else -> content(paddingValues)
        }
    }
}