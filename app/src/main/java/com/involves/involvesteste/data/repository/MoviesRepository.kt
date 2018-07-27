package com.involves.involvesteste.data.repository

import android.arch.paging.LivePagedListBuilder
import android.util.Log
import com.involves.involvesteste.data.MoviesBoundaryCallback
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieResult
import com.involves.involvesteste.data.model.MoviesListResult
import com.involves.involvesteste.data.source.local.MoviesLocalCache
import com.involves.involvesteste.data.source.remote.ApiService
import com.involves.involvesteste.data.source.remote.getMovieForId
import com.involves.involvesteste.utils.Utils

class MoviesRepository(
        private val service : ApiService,
        private val moviesCache : MoviesLocalCache
) {

    fun search(query: String): MoviesListResult {
        // Get data source factory from the local cache
        val dataSourceFactory = moviesCache.getMoviesDb(query)

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = MoviesBoundaryCallback(query, service, moviesCache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return MoviesListResult(data)
    }

    fun detailMovieFromId(movieId : Int) : MovieResult {
        val hasConnection = Utils.isConnectedToInternet()
        if (hasConnection) {
            getMovieForId(service, movieId, {
                it?.let {movieDetail ->

                    if (movieDetail.genres != null) {
                        for (index in movieDetail.genres.indices) {
                            moviesCache.insertGenre(movieDetail.genres[index].id, movieDetail.genres[index].name, movieId, {
                                Log.i(TAG, "inserted ${movieDetail.genres.size} genres on db")
                            })
                        }

                    }

                    val movie = Movie(
                            movieDetail.id,
                            movieDetail.posterPath,
                            movieDetail.overview,
                            movieDetail.releaseDate,
                            movieDetail.originalTitle,
                            movieDetail.title,
                            movieDetail.backdropPath,
                            movieDetail.tagline
                    )

                    moviesCache.editMovie(movie, {})
                }
            }, {
                    error -> Log.d("MoviesRepository", error)
            })
        }
        return MovieResult(moviesCache.getMovieDetailDb(movieId))
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
        private const val TAG = "MoviesRepository"
    }

}
