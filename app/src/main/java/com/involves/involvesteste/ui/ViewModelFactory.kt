package com.involves.involvesteste.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.involves.involvesteste.data.repository.MoviesRepository
import com.involves.involvesteste.ui.moviedetail.MovieDetailViewModel
import com.involves.involvesteste.ui.movies.MoviesViewModel

class ViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}