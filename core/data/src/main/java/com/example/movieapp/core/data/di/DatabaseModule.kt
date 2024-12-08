package com.example.movieapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.core.common.utils.Constants
import com.example.movieapp.core.data.local.dao.FavoriteMovieDao
import com.example.movieapp.core.data.local.dao.GenreDao
import com.example.movieapp.core.data.local.dao.MovieGenreDao
import com.example.movieapp.core.data.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideGenreDao(database: MovieDatabase): GenreDao {
        return database.genreDao()
    }

    @Provides
    @Singleton
    fun provideMovieGenreDao(database: MovieDatabase): MovieGenreDao {
        return database.movieGenreDao()
    }
}