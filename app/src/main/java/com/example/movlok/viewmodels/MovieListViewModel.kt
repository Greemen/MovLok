package com.example.movlok.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movlok.models.MovieModel

class MovieListViewModel: ViewModel() {
    // This class is used for VIEWMODEL

    // Live data
    private val mMovies = MutableLiveData<List<MovieModel>>()

    // If you need to expose mMovies as LiveData:
    fun getMovies(): LiveData<List<MovieModel>> {
        return mMovies
    }


}