package com.christopher.elias.data.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.christopher.elias.data.network.utils.ConnectionUtils
import java.lang.Exception

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class ConnectionUtilsImpl(private val applicationContext: Context) :
    ConnectionUtils {
    override fun isNetworkAvailable(): Boolean {
        try {
            val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw      = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        } catch (e: Exception) {
            Log.e("NetworkUtils", "Exception happened: ${e.message}")
            return false
        }
    }
}