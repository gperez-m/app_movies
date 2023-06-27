package com.unigis.moviesapp.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val dates: Dates,
    val page: Int,
    val results: List<MovieData>? = null,
    val total_pages: Int,
    val total_results: Int
) {
    data class Dates (
        val maximum: String,
        val minimum: String,
    )
}

data class MovieData (
    var id: Int,
    val adult: Boolean,
    var backdrop_path: String,
    var genre_ids: List<Int>,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)