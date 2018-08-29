package br.com.anestech.axreg_droid.retrofit.webclient

import android.util.Log
import br.com.anestech.axcalc.AppConstants
import br.com.anestech.axreg_droid.retrofit.RetrofitInicializer
import br.com.anestech.axreg_droid.retrofit.response.CallbackResponse
import br.com.vinipaulino.challengmovie.model.MovieDetails
import br.com.vinipaulino.challengmovie.model.MovieResponse
import br.com.vinipaulino.challengmovie.model.Movies
import retrofit2.Call
import retrofit2.Response
/**
 * Created by vinicius on 26/06/18.
 */
class MovieWebClient {
    fun getMoviesPremier(callbackResponse: CallbackResponse<List<Movies>>) {

        val call = RetrofitInicializer().movieService().getMoviesPremier(AppConstants.API_KEY, AppConstants.LANGUAGUE)
        call.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response?.isSuccessful!!) {
                    val listOfMovies: List<Movies> = response.body()?.results!!
                    callbackResponse.success(listOfMovies)
                } else if (response?.code() == 401) {
                    callbackResponse.responseFailure()
                }
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                //progressBar.visibility = View.GONE
                Log.e("OnFailure", t.toString())
            }
        })
    }

    fun getMovieDetails(movie_id:String, callbackResponse: CallbackResponse<MovieDetails>) {

        val call = RetrofitInicializer().movieService().getMovieDetails(movie_id.toInt(),
                AppConstants.API_KEY,
                AppConstants.LANGUAGUE)

        call.enqueue(object : retrofit2.Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                if (response?.isSuccessful!!) {
                    val movieDetails: MovieDetails? = response.body()
                    callbackResponse.success(movieDetails!!)
                } else if (response?.code() == 401) {
                    callbackResponse.responseFailure()
                }
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                t?.message?.let {
                    callbackResponse.failure(t!!)
                    Log.e("onFailure error", t?.message)
                }
            }
        })
    }
}