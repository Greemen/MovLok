package com.example.movlok.request

import androidx.lifecycle.MutableLiveData
import com.example.movlok.AppExecutors
import com.example.movlok.models.MovieModel
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class MovieApiClient private constructor() {
    // MutableLiveData holding a list of MovieModel
    private val mMovies = MutableLiveData<List<MovieModel>>()

    // Variable to hold reference to the future task
    private var myHandler: Future<*>? = null

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
    fun SerchMoviesApi() {
        myHandler = AppExecutors.getInstance().networkIO().submit {
            // Retrieve Data from api
            // Update LiveData with the data
        }

        // Cancel the task after a delay (example uses 4 seconds)
        AppExecutors.getInstance().networkIO().schedule({
            myHandler?.cancel(true)
        }, 4000, TimeUnit.MILLISECONDS)
    }
}
