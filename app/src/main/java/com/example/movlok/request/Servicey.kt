package com.example.movlok.request

import com.example.movlok.utils.Credentials
import com.example.movlok.utils.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Servicey {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit: Retrofit = retrofitBuilder.build()

    // Create an instance of your MovieApi interface
    private val movieApi: MovieApi = retrofit.create(MovieApi::class.java)

    fun getMovieApi(): MovieApi {
        return movieApi
    }
}
