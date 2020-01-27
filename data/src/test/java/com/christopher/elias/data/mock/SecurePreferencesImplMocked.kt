package com.christopher.elias.data.mock

import com.christopher.elias.data.preferences.SecurePreferences

/**
 * Created by Christopher Elias on 15/11/2019.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class SecurePreferencesImplMocked(): SecurePreferences {

    override fun saveLogInInfo(token: String, name: String) {
        /*
         * Do something
         */
        println("Saving Login Info")
        println("Name: $name")
        println("Token: $token")
    }

    override fun getAccessToken(): String {
        return ""
    }

    override fun getClientName(): String {
        return "Get Client Name"
    }

    override fun logOut() {
        /*
         * Clear all prefs
         */
        println("log out.")
    }
}