package com.example.movlok.response

import com.example.movlok.models.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// This class is for getting multiple movies/list - popular movies
class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose
    val total_count: Int = 0

    @SerializedName("results")
    @Expose
    val movies: List<MovieModel> = emptyList()
    override fun toString(): String {
        return "MovieSearchResponse(total_count=$total_count, movies=$movies)"
    }
}
