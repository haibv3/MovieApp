package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.common.base.BaseUseCase
import com.example.movieapp.core.domain.model.MovieDetail
import com.example.movieapp.core.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieToFavoritesUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<MovieDetail, Unit>() {
    
    override suspend fun invoke(parameters: MovieDetail) {
        repository.addMovieToFavorites(parameters)
    }
}