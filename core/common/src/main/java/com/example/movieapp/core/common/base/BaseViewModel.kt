package com.example.movieapp.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState, UiEvent : BaseUiEvent, UiAction : BaseUiAction> : ViewModel() {
    
    private val _uiState: MutableStateFlow<UiState> by lazy { MutableStateFlow(initializeUiState()) }
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    abstract fun initializeUiState(): UiState

    abstract fun handleAction(action: UiAction)

    protected fun updateUiState(state: UiState) {
        _uiState.value = state
    }

    protected fun emitUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}