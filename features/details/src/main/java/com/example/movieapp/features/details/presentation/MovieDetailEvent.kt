package com.example.movieapp.features.details.presentation

import com.example.movieapp.core.common.base.BaseUiEvent

sealed interface MovieDetailEvent : BaseUiEvent {
    data class ShowMessage(val message: String) : MovieDetailEvent
}