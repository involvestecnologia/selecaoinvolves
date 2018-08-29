package com.involves.involvesteste.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.involves.involvesteste.data.model.Genre
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieAllGenres
import java.util.concurrent.Executor

class MoviesLocalCache(
        private val moviesDao: MoviesDao,
        private val genresDao: GenresDao,
        private val ioExecutor: Executor
) {

    fun insertMovies(movies: List<Movie>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            moviesDao.insertMovies(movies)
            insertFinished()
        }
    }

    fun insertGenre(id:Int, name:String?, movieId: Int, insertFinished: () -> Unit) {
        val obj = Genre(id, name, movieId)
        ioExecutor.execute {
            genresDao.insert(obj)
            insertFinished()
        }
    }

    fun editMovie(movie: Movie, editFinished: () -> Unit) {
        ioExecutor.execute {
            moviesDao.editMovie(movie)
            editFinished()
        }
    }

    fun getMoviesDb(name: String): DataSource.Factory<Int, Movie> {
        return moviesDao.queryUpcomingMoviesDb()
    }

    fun getMovieDetailDb(movieId: Int): LiveData<MovieAllGenres> {
        return moviesDao.findMoviesWithGenresByMovieId(movieId)
    }
}