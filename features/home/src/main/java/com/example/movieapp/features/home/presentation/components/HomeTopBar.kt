package com.example.movieapp.features.home.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.movieapp.core.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    AppTopBar(
        title = "Popular Movies"
    )
}