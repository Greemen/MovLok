package com.example.movlok.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movlok.models.MovieModel
import com.example.movlok.repositories.MovieRepository

class MovieListViewModel : ViewModel() {
    // This class is used for ViewModel

    // Repository instance
    private val movieRepository = MovieRepository.getInstance()

    // Public LiveData, exposed as read-only
    val movies: LiveData<List<MovieModel>>
        get() = movieRepository.movies
}