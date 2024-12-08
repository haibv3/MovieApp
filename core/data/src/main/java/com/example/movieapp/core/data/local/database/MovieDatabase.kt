package com.example.movieapp.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.core.data.local.dao.FavoriteMovieDao
import com.example.movieapp.core.data.local.dao.GenreDao
import com.example.movieapp.core.data.local.dao.MovieGenreDao
import com.example.movieapp.core.data.local.entity.FavoriteMovieEntity
import com.example.movieapp.core.data.local.entity.GenreEntity
import com.example.movieapp.core.data.local.entity.MovieGenreCrossRef
import com.example.movieapp.core.data.local.utils.DateConverter

@Database(
    entities = [
        FavoriteMovieEntity::class,
        GenreEntity::class,
        MovieGenreCrossRef::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
    abstract fun genreDao(): GenreDao
    abstract fun movieGenreDao(): MovieGenreDao
}