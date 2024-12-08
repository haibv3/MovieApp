package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class SearchMoviesParams(
    val query: String,
    val page: Int
)

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<SearchMoviesParams, Flow<Resource<List<Movie>>>>() {
    
    override suspend fun invoke(parameters: SearchMoviesParams): Flow<Resource<List<Movie>>> {
        return repository.searchMovies(parameters.query, parameters.page)
    }
}