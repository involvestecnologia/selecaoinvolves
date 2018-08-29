package com.involves.involvesteste.data.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data class MoviesResponseApi(
        val page : Int,
        val results : List<Movie>,
        @SerializedName("total_pages") val total : Int
)

data class MovieDetailResponseApi(
        val id : Int,
        @SerializedName("original_title") val originalTitle : String?,
        @SerializedName("title") val title : String?,
        @SerializedName("poster_path") val posterPath : String?,
        val overview : String?,
        @SerializedName("release_date") val releaseDate : String?,
        @SerializedName("backdrop_path") val backdropPath : String?,
        val genres : List<Genre>?,
        val tagline : String?
)

data class MoviesListResult(
        val moviesData: LiveData<PagedList<Movie>>
)

data class MovieResult(
        val movie: LiveData<MovieAllGenres>
)
