package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.domain.repository.MovieRepository
import javax.inject.Inject

class RemoveMovieFromFavoritesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Int, Unit>() {
    
    override suspend fun invoke(parameters: Int) {
        repository.removeMovieFromFavorites(parameters)
    }
}