package com.example.movlok.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val movie_id: Int,
    val movie_overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
) : Parcelable