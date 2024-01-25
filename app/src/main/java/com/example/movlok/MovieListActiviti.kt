package com.example.movlok

import android.credentials.CredentialOption
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.movlok.models.MovieModel
import com.example.movlok.request.Servicey
import com.example.movlok.response.MovieSearchResponse
import com.example.movlok.utils.Credentials
import com.example.movlok.utils.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListActiviti : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button by its ID
        val button: Button = findViewById(R.id.button)

        // Set up a click listener for the button
        button.setOnClickListener {
            Log.v("Tag", "Click")
            //GetRetrofitResponse()
            GetRetrofitResponseAccordingToID()
        }
    }

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

}


