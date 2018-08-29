package br.com.anestech.axreg_droid.retrofit


import br.com.anestech.axcalc.AppConstants
import br.com.anestech.axreg_droid.retrofit.service.MovieService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInicializer() {
    private var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client : OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor)

    val gson = GsonBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()

    fun movieService() = retrofit.create(MovieService::class.java)

}