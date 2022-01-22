package com.tatisam.movie.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStatusChecker(private val connectivityManager: ConnectivityManager) {

    fun performIfConnectedToInternet(action: () -> Unit): Boolean{
        if(hasInternetConnection()){
            action()
            return true
        }
        return false
    }

    private fun hasInternetConnection():Boolean{
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}
