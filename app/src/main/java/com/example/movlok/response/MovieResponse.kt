package com.example.movlok.response

import com.example.movlok.models.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// This represents a single movie response
class MovieResponse {
    // Deserialize the movie object using Gson
    @SerializedName("results")
    @Expose
    private var movie: MovieModel? = null

    // Getter for the movie property
    fun getMovie(): MovieModel? {
        return movie
    }

    override fun toString(): String {
        return "MovieResponse(movie=$movie)"
    }
}