package br.com.vinipaulino.challengmovie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.annotation.RequiresApi

object AndroidUtils {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetworkAvailable(context : Context) : Boolean{
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivity.allNetworks
        for (n in network) {
            val info = connectivity.getNetworkInfo(n)
            if (info.state == NetworkInfo.State.CONNECTED){
                return true
            }
        }
        return false
    }

    //para testar se há conexão com a internet
    //Usar o metodo dessa classe
    //val internetOk = AndroidUtils.isNetworkAvailable(context)
}