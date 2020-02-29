package com.christopher.elias.data.di

import com.christopher.elias.data.BuildConfig
import com.christopher.elias.data.network.end_points.EndPoints
import com.christopher.elias.data.network.end_points.EndPointsImpl
import com.christopher.elias.data.network.end_points.EndPointsService
import com.christopher.elias.data.network.end_points.SupportInterceptor
import com.christopher.elias.data.network.utils.ConnectionUtils
import com.christopher.elias.data.network.utils.ConnectionUtilsImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/


val networkModule = module {
    single<ConnectionUtils> {
        ConnectionUtilsImpl(
            androidContext()
        )
    }
    factory { SupportInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideApi(get()) }
    single { provideRetrofit(get()) }
    single<EndPoints> { EndPointsImpl(get(), get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(authInterceptor: SupportInterceptor): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
    return builder.build()
}

fun provideApi(retrofit: Retrofit): EndPointsService = retrofit.create(EndPointsService::class.java)