package com.example.movlok.request

import androidx.lifecycle.MutableLiveData
import com.example.movlok.models.MovieModel

class MovieApiClient private constructor() {
    // MutableLiveData holding a list of MovieModel
    private val mMovies = MutableLiveData<List<MovieModel>>()

    companion object {
        @Volatile
        private var instance: MovieApiClient? = null

        fun getInstance(): MovieApiClient =
            instance ?: synchronized(this) {
                instance ?: MovieApiClient().also { instance = it }
            }
    }

    // Public method to get movies LiveData
    fun getMovies(): MutableLiveData<List<MovieModel>> = mMovies

    // Method to fetch movies from a data source, which updates the LiveData
    fun fetchMovies() {
        // Fetch data and update LiveData
        // This might involve making a network request or querying a database
        // For example:
        // mMovies.value = listOfMoviesFetchedFromDataSource
    }
}
