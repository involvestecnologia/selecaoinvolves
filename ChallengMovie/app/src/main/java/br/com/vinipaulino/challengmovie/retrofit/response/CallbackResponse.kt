package br.com.anestech.axreg_droid.retrofit.response

/**
 * Created by vinicius on 07/06/18.
 */
interface CallbackResponse<T> {
    fun success(response: T)

    fun failure(throwable: Throwable)

    fun responseFailure()
}