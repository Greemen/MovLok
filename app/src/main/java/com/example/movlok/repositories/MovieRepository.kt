package com.example.movlok.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movlok.models.MovieModel

class MovieRepository private constructor() {
    // Backing property for MutableLiveData
    private val _movies = MutableLiveData<List<MovieModel>>()

    // Public LiveData, exposed as read-only
    val movies: LiveData<List<MovieModel>>
        get() = _movies

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository().also { instance = it }
            }
    }
}