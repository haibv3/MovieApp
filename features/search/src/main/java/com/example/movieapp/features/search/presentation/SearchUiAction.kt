package com.example.movieapp.features.search.presentation

import com.example.movieapp.core.common.base.BaseUiAction

sealed interface SearchUiAction : BaseUiAction {
    data class Search(val query: String) : SearchUiAction
    object LoadMore : SearchUiAction
}