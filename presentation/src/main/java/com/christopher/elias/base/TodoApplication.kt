package com.christopher.elias.base

import android.app.Application
import com.christopher.elias.base.di.mapperPresentationModule
import com.christopher.elias.base.di.viewModelModule
import com.christopher.elias.data.di.mapperDataModule
import com.christopher.elias.data.di.networkModule
import com.christopher.elias.data.di.preferencesModule
import com.christopher.elias.data.di.repositoryModule
import com.christopher.elias.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Christopher Elias on 3/02/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoApplication)
            modules(arrayListOf(networkModule, preferencesModule, mapperDataModule, repositoryModule,
                useCasesModule, mapperPresentationModule, viewModelModule))
        }
    }
}