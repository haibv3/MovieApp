package com.example.movieapp.core.data.repository

import com.example.movieapp.core.common.base.NetworkResult
import com.example.movieapp.core.common.base.Resource
import com.example.movieapp.core.data.local.dao.FavoriteMovieDao
import com.example.movieapp.core.data.local.dao.GenreDao
import com.example.movieapp.core.data.local.dao.MovieGenreDao
import com.example.movieapp.core.data.local.entity.GenreEntity
import com.example.movieapp.core.data.local.entity.MovieGenreCrossRef
import com.example.movieapp.core.data.remote.mapper.toDomainModel
import com.example.movieapp.core.data.remote.mapper.toFavoriteEntity
import com.example.movieapp.core.data.remote.source.MovieRemoteDataSource
import com.example.movieapp.core.domain.model.Genre
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.model.MovieDetail
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val favoriteMovieDao: FavoriteMovieDao,
    private val genreDao: GenreDao,
    private val movieGenreDao: MovieGenreDao
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>> =
        remoteDataSource.getPopularMovies(page).map { result ->
            when (result) {
                is NetworkResult.Success ->
                    Resource.Success(result.data.results.map { it.toDomainModel() })
                is NetworkResult.Error -> 
                    Resource.Error(result.message)
                NetworkResult.Loading -> 
                    Resource.Loading
            }
        }

    override suspend fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> =
        remoteDataSource.getMovieDetail(movieId).map { result ->
            when (result) {
                is NetworkResult.Success -> 
                    Resource.Success(result.data.toDomainModel())
                is NetworkResult.Error -> 
                    Resource.Error(result.message)
                NetworkResult.Loading -> 
                    Resource.Loading
            }
        }

    override suspend fun searchMovies(query: String, page: Int): Flow<Resource<List<Movie>>> =
        remoteDataSource.searchMovies(query, page).map { result ->
            when (result) {
                is NetworkResult.Success -> 
                    Resource.Success(result.data.results.map { it.toDomainModel() })
                is NetworkResult.Error -> 
                    Resource.Error(result.message)
                NetworkResult.Loading -> 
                    Resource.Loading
            }
        }

    override suspend fun getGenres(): Flow<Resource<List<Genre>>> =
        remoteDataSource.getGenres().map { result ->
            when (result) {
                is NetworkResult.Success -> 
                    Resource.Success(result.data.genres.map { it.toDomainModel() })
                is NetworkResult.Error -> 
                    Resource.Error(result.message)
                NetworkResult.Loading -> 
                    Resource.Loading
            }
        }

    override suspend fun getSimilarMovies(movieId: Int, page: Int): Flow<Resource<List<Movie>>> =
        remoteDataSource.getSimilarMovies(movieId, page).map { result ->
            when (result) {
                is NetworkResult.Success -> 
                    Resource.Success(result.data.results.map { it.toDomainModel() })
                is NetworkResult.Error -> 
                    Resource.Error(result.message)
                NetworkResult.Loading -> 
                    Resource.Loading
            }
        }

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        favoriteMovieDao.getAllFavoriteMovies().map { favoritesmMovie ->
            favoritesmMovie.map { entity ->
                Movie(
                    id = entity.movieId,
                    title = entity.title,
                    overview = entity.overview,
                    posterPath = entity.posterPath,
                    backdropPath = entity.backdropPath,
                    releaseDate = entity.releaseDate,
                    genreIds = emptyList(),
                    voteAverage = entity.voteAverage,
                    voteCount = entity.voteCount
                )
            }
        }

    override suspend fun addMovieToFavorites(movieDetail: MovieDetail) {
        favoriteMovieDao.insertFavoriteMovie(movieDetail.toFavoriteEntity())
        
        movieDetail.genres.forEach { genre ->
            movieGenreDao.insertMovieGenreCrossRef(
                MovieGenreCrossRef(movieDetail.id, genre.id)
            )
        }
        genreDao.insertGenres(movieDetail.genres.map { GenreEntity(it.id, it.name) })
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        favoriteMovieDao.deleteFavoriteMovieById(movieId)
        movieGenreDao.deleteMovieGenreCrossRefs(movieId)
    }

    override fun isMovieFavorite(movieId: Int): Flow<Boolean> =
        favoriteMovieDao.isFavorite(movieId)
}