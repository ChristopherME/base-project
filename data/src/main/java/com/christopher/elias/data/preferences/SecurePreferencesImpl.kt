package com.christopher.elias.data.preferences

import android.content.SharedPreferences
import com.christopher.elias.data.di.PREF_KEY_ACCESS_TOKEN
import com.christopher.elias.data.di.PREF_KEY_USER_NAME

/**
 * Created by Christopher Elias on 30/09/2019.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/

class SecurePreferencesImpl(private val prefs : SharedPreferences) : SecurePreferences {

    override fun saveLogInInfo(token: String, name: String) {
        prefs.edit().putString(PREF_KEY_ACCESS_TOKEN, "Bearer $token").apply()
        prefs.edit().putString(PREF_KEY_USER_NAME, name).apply()
    }

    override fun getAccessToken() = prefs.getString(PREF_KEY_ACCESS_TOKEN, "")?:""

    override fun getClientName() = prefs.getString(PREF_KEY_USER_NAME, "")?:""

    override fun logOut() = prefs.edit().clear().apply()
}