package com.example.movieapp.features.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.base.BaseViewModel
import com.example.movieapp.core.domain.usecase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : BaseViewModel<FavoritesUiState, FavoritesUiEvent, FavoritesUiAction>() {

    init {
        getFavoriteMovies()
    }

    override fun initializeUiState(): FavoritesUiState = FavoritesUiState()

    override fun handleAction(action: FavoritesUiAction) {
        when (action) {
            is FavoritesUiAction.RefreshFavorites -> getFavoriteMovies()
        }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            movieUseCases.getFavoriteMovies(Unit).collectLatest { movies ->
                updateUiState(
                    uiState.value.copy(
                        movies = movies
                    )
                )
            }
        }
    }
}