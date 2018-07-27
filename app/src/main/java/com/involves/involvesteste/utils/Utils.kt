package com.involves.involvesteste.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.involves.involvesteste.InvolvesTestApplication
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun isConnectedToInternet(): Boolean {
        val connectivity = InvolvesTestApplication.instance.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
        }
        return false
    }

    fun formatDate(fromPattern: String, toPattern: String, value: String) : String {
        val sdf = SimpleDateFormat(fromPattern, Locale.getDefault())
        val date = sdf.parse(value)
        sdf.applyPattern(toPattern)
        return sdf.format(date)
    }
}