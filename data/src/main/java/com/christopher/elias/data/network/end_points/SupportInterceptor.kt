package com.christopher.elias.data.network.end_points

import com.christopher.elias.data.preferences.SecurePreferences
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
class SupportInterceptor(private val preferences : SecurePreferences) : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", preferences.getAccessToken())
            .build()
        return chain.proceed(request)
    }
}