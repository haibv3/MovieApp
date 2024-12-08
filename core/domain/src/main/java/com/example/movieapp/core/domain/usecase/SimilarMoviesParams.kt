package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class SimilarMoviesParams(
    val movieId: Int,
    val page: Int
)

class GetSimilarMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<SimilarMoviesParams, Flow<Resource<List<Movie>>>>() {

    override suspend fun invoke(parameters: SimilarMoviesParams): Flow<Resource<List<Movie>>> {
        return repository.getSimilarMovies(parameters.movieId, parameters.page)
    }
}