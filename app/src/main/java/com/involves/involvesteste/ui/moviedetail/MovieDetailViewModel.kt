package com.involves.involvesteste.ui.moviedetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieAllGenres
import com.involves.involvesteste.data.model.MovieResult
import com.involves.involvesteste.data.repository.MoviesRepository

class MovieDetailViewModel(
        private val repository: MoviesRepository
) : ViewModel() {

    private val queryLiveData = MutableLiveData<Int>()
    private val movieResult: LiveData<MovieResult> = Transformations.map(queryLiveData, {
        repository.detailMovieFromId(it)
    })

    val movieDetail: LiveData<MovieAllGenres> = Transformations.switchMap(movieResult, {
        it.movie
    })

    fun getMovieDetail(id: Int) {
        queryLiveData.postValue(id)
//        queryLiveData.postValue(id)
//        return repository.detailMovieFromId(id)
    }
}