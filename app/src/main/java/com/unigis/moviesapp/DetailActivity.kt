package com.unigis.moviesapp

import android.app.ActionBar.LayoutParams
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.marginLeft
import androidx.core.view.setMargins
import com.squareup.picasso.Picasso
import com.unigis.moviesapp.model.Detail
import com.unigis.moviesapp.network.Api
import com.unigis.moviesapp.network.ClientConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieData = intent.getStringExtra("idMovie")!!
        getDetailMovie(movieData)
    }

     private fun getDetailMovie(id: String) {
         Api.retrofitService.getDetailMovies(id).enqueue(object: Callback<Detail> {
             @RequiresApi(Build.VERSION_CODES.O)
             override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                 if(response.isSuccessful) {
                     loadDataActivity(response.body())
                 }
             }

             override fun onFailure(call: Call<Detail>, t: Throwable) {
                 t.printStackTrace()
             }

         })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadDataActivity(detail: Detail?){
        val detailTitle = findViewById<TextView>(R.id.detailTitle)
        val detailDescription = findViewById<TextView>(R.id.detailDescription)
        val detailDate = findViewById<TextView>(R.id.detailDate)
        val detailImage = findViewById<ImageView>(R.id.detailImage)
        val date = LocalDate.parse(detail?.release_date)
        val detailLayout = findViewById<LinearLayout>(R.id.linearViews)

        if (detail != null) {
            val genres = detail.genres?.size?:0

            detailTitle.text = detail.original_title
            detailDate.text = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
            detailDescription.text = detail.overview
            Picasso.get().load(ClientConstants.URL_POSTER + detail.poster_path).into(detailImage)

            if (genres != 0) {
                detail.genres?.forEach { it ->
                    val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                    val textView = TextView(this)
                    textView.text = it.name
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                    textView.setTextColor(Color.BLACK)
                    textView.setBackgroundResource(R.drawable.rounded_view)
                    textView.setPadding(10,10,10,10)
                    layoutParams.setMargins(0,0,15,0)
                    textView.layoutParams = layoutParams
                    detailLayout.addView(textView)
                }
            }
        }

    }
}