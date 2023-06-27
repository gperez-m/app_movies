package com.unigis.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unigis.moviesapp.Adapter.NowPlayingAdapter
import com.unigis.moviesapp.Adapter.PopularAdapter
import com.unigis.moviesapp.model.Movie
import com.unigis.moviesapp.model.Popular
import com.unigis.moviesapp.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var managerH: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)
        managerH = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getMoviesPopular()
        getNowPlaying()
    }

    private fun getMoviesPopular(){
        Api.retrofitService.getMostPopular().enqueue(object: Callback<Popular> {
            override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                if(response.isSuccessful) {
                    recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVertical).apply {
                        myAdapter = PopularAdapter(response.body()?.results ?: emptyList()){ idMovie -> getDetailsMovie(idMovie)}
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Popular>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getNowPlaying(){
        Api.retrofitService.getNowPlaying().enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful) {
                    recyclerView = findViewById<RecyclerView?>(R.id.recyclerViewHorizontal).apply {
                        myAdapter = NowPlayingAdapter(response.body()?.results ?: emptyList())
                        layoutManager = managerH
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getDetailsMovie(idMovie: Int) {
        Log.i("Index", idMovie.toString())
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idMovie", idMovie.toString())
        startActivity(intent)
    }
}