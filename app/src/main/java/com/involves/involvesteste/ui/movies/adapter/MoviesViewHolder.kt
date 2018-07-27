package com.involves.involvesteste.ui.movies.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.involves.involvesteste.R
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.ui.moviedetail.MovieDetailActivity
import com.involves.involvesteste.utils.Constants
import com.involves.involvesteste.utils.Utils
import kotlinx.android.synthetic.main.adapter_movies.view.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(movie : Movie) {
        val titleMovie = itemView.txt_title_movie
        val releaseDate = itemView.txt_release_date
        val imgPoster = itemView.img_poster

        titleMovie.text = " "
        releaseDate.text = " - "

        movie.title?.let {
            titleMovie.text = it
        }

        movie.releaseDate?.let {
            releaseDate.text = Utils.formatDate("yyyy-MM-dd", "dd/MM/yyyy", it)
        }

        Glide.with(itemView.context)
                .load(Constants.IMG_BASE_URL + movie.posterPath)
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(R.drawable.no_image_placeholder)
                        .error(R.drawable.no_image_placeholder)
                )
                .into(imgPoster)

        itemView.setOnClickListener {
            movie.let { movie ->
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra("movie_id", movie.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_movies, parent, false)
            return MoviesViewHolder(view)
        }
    }
}