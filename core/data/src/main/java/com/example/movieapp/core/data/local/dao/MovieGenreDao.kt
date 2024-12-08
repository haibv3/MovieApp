package com.example.movieapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movieapp.core.data.local.entity.MovieGenreCrossRef
import com.example.movieapp.core.data.local.entity.MovieWithGenres

@Dao
interface MovieGenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreCrossRef(crossRef: MovieGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM favorite_movies WHERE movieId = :movieId")
    suspend fun getMovieWithGenres(movieId: Int): MovieWithGenres?

    @Query("DELETE FROM movie_genre_cross_ref WHERE movieId = :movieId")
    suspend fun deleteMovieGenreCrossRefs(movieId: Int)
}