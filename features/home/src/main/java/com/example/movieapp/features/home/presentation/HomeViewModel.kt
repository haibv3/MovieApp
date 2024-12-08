package com.example.movieapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.usecase.GetPopularMoviesUseCase
import com.example.movieapp.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel<HomeUiState, HomeUiAction, HomeUiEvent>() {

    private var currentPage = 1
    private var isLastPage = false
    private var loadJob: Job? = null

    init {
        loadMovies(forceRefresh = true)
    }

    override fun createInitialState() = HomeUiState()

    override fun handleAction(action: HomeUiAction) {
        when (action) {
            HomeUiAction.LoadMoreMovies -> {
                if (!uiState.value.isLoading && !isLastPage) {
                    loadMovies(forceRefresh = false)
                }
            }
            HomeUiAction.RefreshMovies -> {
                currentPage = 1
                isLastPage = false
                loadMovies(forceRefresh = true)
            }
        }
    }

    private fun loadMovies(forceRefresh: Boolean) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            getPopularMoviesUseCase(currentPage)
                .onStart { 
                    if (forceRefresh) setState { copy(isLoading = true) }
                }
                .catch { error ->
                    setState { 
                        copy(
                            error = error.message ?: "Unknown error occurred",
                            isLoading = false
                        )
                    }
                }
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            val newMovies = result.data
                            if (newMovies.isEmpty()) {
                                isLastPage = true
                            } else {
                                currentPage++
                                setState {
                                    copy(
                                        movies = if (forceRefresh) newMovies else movies + newMovies,
                                        isLoading = false,
                                        error = null
                                    )
                                }
                            }
                        }
                        is Resource.Error -> {
                            setState {
                                copy(
                                    error = result.message,
                                    isLoading = false
                                )
                            }
                        }
                        is Resource.Loading -> {
                            if (!forceRefresh) {
                                setState { copy(isLoading = true) }
                            }
                        }
                    }
                }
        }
    }
}