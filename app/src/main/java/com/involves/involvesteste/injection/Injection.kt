package com.involves.involvesteste.injection

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.involves.involvesteste.InvolvesTestApplication
import com.involves.involvesteste.data.repository.MoviesRepository
import com.involves.involvesteste.data.source.local.MoviesDao
import com.involves.involvesteste.data.source.local.MoviesLocalCache
import com.involves.involvesteste.data.source.remote.ApiService
import com.involves.involvesteste.ui.ViewModelFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


object Injection {

    private var retrofitInstance: Retrofit? = null
    private var okHttpClient: OkHttpClient.Builder? = null

//    fun provideRemoteRepository(): MoviesRepository {
//        return MoviesRepository(providesRemoteApi(), providesMoviesDao())
//    }

    private fun providesRemoteApi(): ApiService {
        return providesRetrofitInstance(providesUrl())!!.create<ApiService>(ApiService::class.java)
    }


    private fun providesRetrofitInstance(serverUrl: String): Retrofit? {
        if (retrofitInstance == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(serverUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(providesOkHttpClient())


            retrofitInstance = retrofit.build()
        }

        return retrofitInstance
    }

    private fun providesOkHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder()
                        .addQueryParameter("api_key", "4c3e0caac87ffa519d959a44cd67defd")
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
        }

        return okHttpClient!!.build()
    }

    private fun providesUrl(): String {
        return "https://api.themoviedb.org/3/"
    }

    private fun provideCache(context: Context): MoviesLocalCache {
        val database = InvolvesTestApplication.instance.getDatabase()
        return MoviesLocalCache(database.moviesDao(), database.genresDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepository(providesRemoteApi(), provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideMoviesRepository(context))
    }

}
