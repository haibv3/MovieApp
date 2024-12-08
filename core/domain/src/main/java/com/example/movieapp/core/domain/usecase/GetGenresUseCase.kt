package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.domain.model.Genre
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<Unit, Flow<Resource<List<Genre>>>>() {
    
    override suspend fun invoke(parameters: Unit): Flow<Resource<List<Genre>>> {
        return repository.getGenres()
    }
}