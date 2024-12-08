package com.example.movieapp.core.common.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchIO(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Unit
) {
    viewModelScope.launch(dispatcher) {
        block()
    }
}