package com.unigis.moviesapp.model

data class Popular(
    val page: Int,
    val results: List<MovieData>,
    val total_pages: Int,
    val total_results: Int
)
