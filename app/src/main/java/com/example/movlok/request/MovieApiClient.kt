package com.example.movlok.request

import androidx.lifecycle.MutableLiveData
import com.example.movlok.AppExecutors
import com.example.movlok.models.MovieModel
import com.example.movlok.response.MovieSearchResponse
import com.example.movlok.utils.Credentials
import retrofit2.Call
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class MovieApiClient private constructor() {
    // MutableLiveData holding a list of MovieModel
    val mMovies = MutableLiveData<List<MovieModel>>()

    // Variable to hold reference to the runnable task
    private var retrieveMoviesRunnable: RetrieveMoviesRunnable? = null

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
    fun searchMoviesApi(query: String, pageNumber: Int) {
        // If a previous runnable exists, cancel it before creating a new one
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable!!.cancelRequest()
        }
        retrieveMoviesRunnable = RetrieveMoviesRunnable(query, pageNumber)
        val myHandler: Future<*> = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable)

        // Cancel the task after a delay (example uses 4 seconds)
        AppExecutors.getInstance().networkIO().schedule({
            myHandler.cancel(true)
        }, 4000, TimeUnit.MILLISECONDS)
    }
}

class RetrieveMoviesRunnable(private val query: String, private val pageNumber: Int) : Runnable {
    @Volatile
    private var cancelRequest = false

    override fun run() {
        if (cancelRequest) {
            return
        }

        val servicey = Servicey()
        val movieApi = servicey.getMovieApi()
        val responseCall: Call<MovieSearchResponse> = movieApi.searchMovie(
            Credentials.API_KEY,
            query,
            pageNumber.toString()
        )

        try {
            val response = responseCall.execute() // Synchronous execution

            if (cancelRequest) {
                return
            }

            if (response.isSuccessful) {
                val movieSearchResponse = response.body()
                val movieList = movieSearchResponse?.movies ?: emptyList()
                MovieApiClient.getInstance().mMovies.postValue(movieList)
            } else {
                // Handle the error scenario, such as displaying an error message
            }
        } catch (e: Exception) {
            // Handle the exception, such as network error, logging the exception
        }
    }

    // Method to cancel the request
    fun cancelRequest() {
        cancelRequest = true
        // Interrupt the current thread
        Thread.currentThread().interrupt()
    }
}
