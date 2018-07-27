package com.involves.involvesteste.ui.movies.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.involves.involvesteste.data.model.Movie

class MoviesAdapter : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MOVIE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
            (holder as MoviesViewHolder).bindView(movie)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem == newItem
        }
    }
}