package com.unigis.moviesapp.model

data class Detail(
    val genres: List<Genres>? = null,
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)

data class Genres (
    val id: Int,
    val name: String
)
