package com.example.movieapp.features.details.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.base.BaseViewModel
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.usecase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : BaseViewModel<MovieDetailState, MovieDetailEvent, MovieDetailAction>() {

    override fun initializeUiState(): MovieDetailState = MovieDetailState()

    override fun handleAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.LoadMovieDetail -> loadMovieDetail(action.movieId)
            is MovieDetailAction.AddToFavorites -> addToFavorites()
            is MovieDetailAction.RemoveFromFavorites -> removeFromFavorites()
        }
    }

    private fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieUseCases.getMovieDetail(movieId).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        updateUiState(uiState.value.copy(isLoading = true))
                    }
                    is Resource.Success -> {
                        updateUiState(uiState.value.copy(
                            isLoading = false,
                            movie = result.data,
                            error = null
                        ))
                        checkFavoriteStatus(movieId)
                    }
                    is Resource.Error -> {
                        updateUiState(uiState.value.copy(
                            isLoading = false,
                            error = result.message
                        ))
                    }
                }
            }
        }
    }

    private fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            movieUseCases.isMovieFavorite(movieId).collectLatest { isFavorite ->
                updateUiState(uiState.value.copy(isFavorite = isFavorite))
            }
        }
    }

    private fun addToFavorites() {
        viewModelScope.launch {
            uiState.value.movie?.let { movie ->
                movieUseCases.addMovieToFavorites(movie)
                emitUiEvent(MovieDetailEvent.ShowMessage("Added to favorites"))
            }
        }
    }

    private fun removeFromFavorites() {
        viewModelScope.launch {
            uiState.value.movie?.let { movie ->
                movieUseCases.removeMovieFromFavorites(movie.id)
                emitUiEvent(MovieDetailEvent.ShowMessage("Removed from favorites"))
            }
        }
    }
}