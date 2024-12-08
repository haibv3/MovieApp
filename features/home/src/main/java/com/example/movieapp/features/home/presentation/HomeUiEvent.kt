package com.example.movieapp.features.home.presentation

import com.example.movieapp.core.common.base.BaseUiEvent

sealed interface HomeUiEvent : BaseUiEvent {
    data class NavigateToDetail(val movieId: Int) : HomeUiEvent
}