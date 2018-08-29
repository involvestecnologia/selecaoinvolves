package com.involves.involvesteste.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieAllGenres
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun queryUpcomingMovies() : Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun editMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    fun queryUpcomingMoviesDb() : DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun queryDetailMovieDb(movieId: Int) : LiveData<Movie>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun findMoviesWithGenresByMovieId(movieId: Int) : LiveData<MovieAllGenres>
}