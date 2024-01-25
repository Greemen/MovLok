package com.example.movlok.utils

import com.example.movlok.models.MovieModel
import com.example.movlok.response.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    // Search for movies
    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") key: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): Call<MovieSearchResponse>

    // Search using ID  550 = Fight Club
    @GET("3/movie/{movie_id}?")
    fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<MovieModel>
}