package com.unigis.moviesapp.Adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unigis.moviesapp.R
import com.unigis.moviesapp.model.MovieData
import com.unigis.moviesapp.network.ClientConstants
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PopularAdapter(private val data: List<MovieData>, val onClickItem: (Int) -> Unit) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(movieData: MovieData){
            val mvTitle = view.findViewById<TextView>(R.id.movieTitle)
            val imgMovie = view.findViewById<ImageView>(R.id.movieImage)
            val dateMovie = view.findViewById<TextView>(R.id.movieDate)
            val langMovie = view.findViewById<TextView>(R.id.movieLang)
            val valueMovie = view.findViewById<TextView>(R.id.movieValue);

            view.setOnClickListener{onClickItem(movieData.id)}

            var date = LocalDate.parse(movieData.release_date)

            mvTitle.text = movieData.title
            dateMovie.text = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
            langMovie.text = movieData.original_language.uppercase()
            Glide.with(view.context).load(ClientConstants.URL_POSTER + movieData.poster_path).centerInside().into(imgMovie)
            valueMovie.text = (movieData.vote_average * 10).toInt().toString() + "%"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PopularViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(popularHolder: PopularViewHolder, position: Int) {
        popularHolder.bind(data[position])
    }
}