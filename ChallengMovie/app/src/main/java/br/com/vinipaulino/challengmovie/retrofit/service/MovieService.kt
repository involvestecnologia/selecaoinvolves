package br.com.anestech.axreg_droid.retrofit.service


import br.com.vinipaulino.challengmovie.model.MovieDetails
import br.com.vinipaulino.challengmovie.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by VinÍcius Paulino on 25/06/18.
 */
interface MovieService {

    @GET("movie/top_rated?")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/upcoming?")
    fun getMoviesPremier(@Query("api_key") apiKey: String, @Query("language") language: String ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String, @Query("language") language: String): Call<MovieDetails>

}