package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Int, Flow<Resource<List<Movie>>>>() {
    
    override suspend fun invoke(parameters: Int): Flow<Resource<List<Movie>>> {
        return repository.getPopularMovies(parameters)
    }
}