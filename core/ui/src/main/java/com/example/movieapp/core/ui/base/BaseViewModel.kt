package com.example.movieapp.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Action, Event> : ViewModel() {
    private val _uiState = MutableStateFlow(createInitialState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<Event>()
    val uiEvent = _uiEvent.asSharedFlow()

    abstract fun createInitialState(): State

    abstract fun handleAction(action: Action)

    protected fun setState(reducer: State.() -> State) {
        val newState = uiState.value.reducer()
        _uiState.value = newState
    }

    protected fun sendEvent(event: Event) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}
