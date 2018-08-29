package com.involves.involvesteste.data.source.remote

import android.util.Log
import com.involves.involvesteste.data.model.Genre
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieDetailResponseApi
import com.involves.involvesteste.data.model.MoviesResponseApi
import com.involves.involvesteste.utils.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page : Int, @Query("language") language : String) : Call<MoviesResponseApi>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId : Int, @Query("language") language : String) : Call<MovieDetailResponseApi>

}

fun searchMovies(
        service: ApiService,
        query: String,
        page: Int,
        onSuccess: (moviesList: List<Movie>) -> Unit,
        onError: (error: String) -> Unit) {
//        Log.d("ApiService", "query: $query, page: $page, itemsPerPage: $itemsPerPage")

    service.getUpcomingMovies(page, Constants.LANGUAGE).enqueue(
            object : Callback<MoviesResponseApi> {
                override fun onFailure(call: Call<MoviesResponseApi>?, t: Throwable) {
//                    Log.d(TAG, "fail to get data")
                    onError(t.message ?: "unknown")
                }

                override fun onResponse(
                        call: Call<MoviesResponseApi>?,
                        responseApi: Response<MoviesResponseApi>
                ) {
//                    Log.d(TAG, "got a responseApi $responseApi")
                    if (responseApi.isSuccessful) {
                        val movies = responseApi.body()?.results ?: emptyList()
                        onSuccess(movies)
                    } else {
                        onError(responseApi.errorBody()?.string() ?: "Unknown")
                    }
                }
            }
    )
}

fun getMovieForId(
        service: ApiService,
        movieId: Int,
        onSuccess: (movie: MovieDetailResponseApi?) -> Unit,
        onError: (error: String) -> Unit) {

    service.getDetailMovie(movieId, Constants.LANGUAGE)
            .enqueue(
                    object : Callback<MovieDetailResponseApi> {
                        override fun onFailure(call: Call<MovieDetailResponseApi>?, t: Throwable) {
                            onError(t.message ?: "unknown")
                        }

                        override fun onResponse(call: Call<MovieDetailResponseApi>?, response: Response<MovieDetailResponseApi>) {
                            if (response.isSuccessful) {
                                val movie = response.body()
                                onSuccess(movie)
                            } else {
                                onError(response.errorBody()?.string() ?: "Unknown")
                            }
                        }
                    }
            )
        }