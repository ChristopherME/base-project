package com.christopher.elias.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.christopher.elias.data.preferences.SecurePreferences
import com.christopher.elias.data.preferences.SecurePreferencesImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/

/**
 * Multiple preferences files
 * more information: https://medium.com/@prus.piotr/multiple-shared-preferences-how-to-manage-them-with-koin-di-dbebeb95b121
 */
val preferencesModule = module {
    single(named("securePrefs")) { provideSecurePreferences(androidApplication()) }
    single<SecurePreferences> { SecurePreferencesImpl(get(named("securePrefs"))) }
}

private const val SECURE_PREFS_FILE_KEY = "com.christopher.elias.secure_preferences"

const val PREF_KEY_ACCESS_TOKEN = "key_user_access_token"
const val PREF_KEY_USER_NAME = "key_user_name"

private fun provideSecurePreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(SECURE_PREFS_FILE_KEY, Context.MODE_PRIVATE)