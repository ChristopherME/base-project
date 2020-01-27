package com.christopher.elias.data.preferences

/**
 * Created by Christopher Elias on 30/09/2019.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
interface SecurePreferences {
    fun saveLogInInfo(token: String, name: String)
    fun getAccessToken() : String
    fun getClientName(): String
    /**
     * Clear all values from pref file.
     */
    fun logOut()
}