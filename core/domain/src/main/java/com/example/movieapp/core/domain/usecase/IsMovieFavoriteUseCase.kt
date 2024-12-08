package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Int, Flow<Boolean>>() {
    
    override suspend fun invoke(parameters: Int): Flow<Boolean> {
        return repository.isMovieFavorite(parameters)
    }
}