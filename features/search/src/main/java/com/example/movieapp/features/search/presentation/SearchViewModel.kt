package com.example.movieapp.features.search.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.base.BaseViewModel
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.usecase.MovieUseCases
import com.example.movieapp.core.domain.usecase.SearchMoviesParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : BaseViewModel<SearchUiState, SearchUiEvent, SearchUiAction>() {

    private var searchJob: Job? = null
    private var currentPage = 1
    private var currentQuery = ""

    override fun initializeUiState(): SearchUiState = SearchUiState()

    override fun handleAction(action: SearchUiAction) {
        when (action) {
            is SearchUiAction.Search -> searchMovies(action.query)
            is SearchUiAction.LoadMore -> loadMoreMovies()
        }
    }

    private fun searchMovies(query: String) {
        searchJob?.cancel()
        if (query.isBlank()) {
            updateUiState(SearchUiState())
            return
        }
        
        currentQuery = query
        currentPage = 1
        
        searchJob = viewModelScope.launch {
            delay(500) // Debounce search
            search()
        }
    }

    private fun loadMoreMovies() {
        if (!uiState.value.isLoading && currentQuery.isNotBlank()) {
            currentPage++
            search()
        }
    }

    private fun search() {
        viewModelScope.launch {
            movieUseCases.searchMovies(
                SearchMoviesParams(currentQuery, currentPage)
            ).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        updateUiState(uiState.value.copy(isLoading = true))
                    }
                    is Resource.Success -> {
                        updateUiState(
                            uiState.value.copy(
                                isLoading = false,
                                movies = if (currentPage == 1) result.data else uiState.value.movies + result.data,
                                error = null
                            )
                        )
                    }
                    is Resource.Error -> {
                        updateUiState(
                            uiState.value.copy(
                                isLoading = false,
                                error = result.message
                            )
                        )
                    }
                }
            }
        }
    }
}