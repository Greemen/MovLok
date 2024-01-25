package com.example.movlok.repositories

import androidx.lifecycle.LiveData
import com.example.movlok.models.MovieModel
import com.example.movlok.request.MovieApiClient

class MovieRepository private constructor() {
    // Acts as repositories

    // Lazy initialization of MovieApiClient
    private val movieApiClient by lazy { MovieApiClient.getInstance() }

    // Exposes the LiveData from MovieApiClient
    val movies: LiveData<List<MovieModel>>
        get() = movieApiClient.getMovies()

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository().also { instance = it }
            }
    }

    // Call the method
    fun searchMovieApi(query: String, pageNumber: Int) {
        movieApiClient.searchMoviesApi(query, pageNumber)
    }
}

