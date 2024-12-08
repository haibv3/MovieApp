package com.example.movieapp.core.data.remote.api

object ApiConstants {
    const val DEFAULT_PAGE = 1
    const val DEFAULT_LANGUAGE = "en-US"
    
    object Endpoints {
        const val POPULAR_MOVIES = "movie/popular"
        const val MOVIE_DETAIL = "movie/{movie_id}"
        const val SEARCH_MOVIES = "search/movie"
        const val GENRES = "genre/movie/list"
        const val SIMILAR_MOVIES = "movie/{movie_id}/similar"
    }
    
    object Params {
        const val PAGE = "page"
        const val LANGUAGE = "language"
        const val QUERY = "query"
        const val MOVIE_ID = "movie_id"
    }
}