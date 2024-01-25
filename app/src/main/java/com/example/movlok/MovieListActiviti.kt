package com.example.movlok

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movlok.viewmodels.MovieListViewModel


class MovieListActiviti : AppCompatActivity() {

    // ViewModel
    private lateinit var movieListViewModel: MovieListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ViewModel using ViewModelProvider
        movieListViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]

        //Calling the observers
        ObserveAnyChange();

        // Testing Method
        val button: Button = findViewById(R.id.button)

        // Set up a click listener for the button
        button.setOnClickListener {
            Log.v("Tag", "Click")
            searchMovieApi("Fast",1)
        }





    }

    // Listening for any live data changes - observing
    // Observing any data changes
    private fun ObserveAnyChange() {
        movieListViewModel.movies.observe(this, Observer { movieModels ->
            // Observing for any data change
            movieModels?.forEach { movieModel ->
                // Get the data in log
                Log.v("Tag", "onChanged: ${movieModel.title}")
            }
        })
    }

    //Call in Main Activity
    private fun searchMovieApi(query: String, pageNumber: Int){
        movieListViewModel.searchMovieApi(query,pageNumber)
    }








    /*
    private fun GetRetrofitResponse() {
        // Creating an instance of the Servicey class to get the MovieApi
        val servicey = Servicey()
        val movieApi = servicey.getMovieApi()

        // Enqueueing the Retrofit call
        val responseCall = movieApi.searchMovie(
            Credentials.API_KEY,
            "Action",
            "1")

        responseCall.enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                if (response.isSuccessful) {
                    val movies: List<MovieModel> = response.body()?.movies ?: listOf()

                    // Log the whole response
                    //Log.v("Tag", "The response: ${response.body().toString()}")

                    // Iterate through the movies and log their release date
                    for (movie in movies) {
                        Log.v("Tag", "Name: ${movie.title}" + " ${movie.release_date}")
                    }

                    // Handle the response here, such as updating UI or storing the data
                } else {
                    // Handle the error scenario, such as displaying an error message
                    Log.e("Tag", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                // Handle failure, such as network error, logging the exception
                Log.e("Tag", "Failure: ${t.message}")
            }
        })
    }

    private fun GetRetrofitResponseAccordingToID(){
        val servicey = Servicey()
        val movieApi = servicey.getMovieApi()

        // Enqueueing the Retrofit call
        val responseCall = movieApi.getMovie(
            343611,
            Credentials.API_KEY
        )

        responseCall.enqueue(object : Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if (response.isSuccessful) {
                    // Log the movie title
                    val movieTitle = response.body()?.title
                    Log.v("Tag", "Movie Title: $movieTitle")
                } else {
                    // Log the error body
                    val errorBody = response.errorBody()?.string()
                    Log.e("Tag", "Error: $errorBody")
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                // Handle failure, such as network error, logging the exception
                Log.e("Tag", "Failure: ${t.message}")
            }
        })
    }
    */

}


