package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Unit, Flow<List<Movie>>>() {
    
    override suspend fun invoke(parameters: Unit): Flow<List<Movie>> {
        return repository.getFavoriteMovies()
    }
}