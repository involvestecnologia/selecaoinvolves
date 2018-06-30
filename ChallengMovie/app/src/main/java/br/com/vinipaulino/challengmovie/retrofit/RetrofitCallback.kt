package br.com.anestech.axreg_droid.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success

/**
 * Created by vinicius on 07/06/18.
 */
fun <T> callback(response: (response: Response<T>?) -> Unit,
                 failure: (throwable: Throwable?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            response(response)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            failure(t)
        }
    }
}