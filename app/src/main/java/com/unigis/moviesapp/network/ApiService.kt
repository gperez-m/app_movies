package com.unigis.moviesapp.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.unigis.moviesapp.model.Detail
import com.unigis.moviesapp.model.Movie
import com.unigis.moviesapp.model.Popular
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ApiService {

    @GET("popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=1")
    fun getMostPopular():Call<Popular>

    @GET("now_playing?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=1")
    fun getNowPlaying():Call<Movie>

    @GET("{id}?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US")
    fun getDetailMovies(@Path("id") id: String):Call<Detail>
}

object Api {
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}