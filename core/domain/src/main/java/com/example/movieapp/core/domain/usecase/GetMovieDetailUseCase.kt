package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.MovieDetail
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Int, Flow<Resource<MovieDetail>>>() {
    
    override suspend fun invoke(parameters: Int): Flow<Resource<MovieDetail>> {
        return repository.getMovieDetail(parameters)
    }
}