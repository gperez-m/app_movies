package com.unigis.moviesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unigis.moviesapp.R
import com.unigis.moviesapp.model.MovieData
import com.unigis.moviesapp.network.ClientConstants

class NowPlayingAdapter(private val data: List<MovieData>) : RecyclerView.Adapter<NowPlayingAdapter.PlayingViewHolder>() {
    class PlayingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieData: MovieData){
            val imgMovie = view.findViewById<ImageView>(R.id.movieImageHorizontal)
            Glide.with(view.context).load(ClientConstants.URL_POSTER + movieData.poster_path).centerInside().into(imgMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_horizontal, parent, false)
        return PlayingViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(playingHolder: PlayingViewHolder, position: Int) {
        playingHolder.bind(data[position])
    }
}