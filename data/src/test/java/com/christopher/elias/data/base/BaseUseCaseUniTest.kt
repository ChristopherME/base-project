package com.christopher.elias.data.base

import com.christopher.elias.data.di.mapperDataModule
import com.christopher.elias.data.di.repositoryModule
import com.christopher.elias.data.mock.fakeNetworkModule
import com.christopher.elias.data.mock.SecurePreferencesImplMocked
import com.christopher.elias.data.preferences.SecurePreferences
import com.christopher.elias.domain.di.useCasesModule
import com.christopher.elias.domain.entity.Failure
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
abstract class BaseUseCaseUniTest : AutoCloseKoinTest() {

    @Before
    fun before() {
        stopKoin() // to remove 'A Koin Application has already been started' exception at the beginning of the test.
        val fakePreferencesModule = module {
            single { SecurePreferencesImplMocked() } bind SecurePreferences::class
        }
        startKoin {
            modules(arrayListOf(fakePreferencesModule,
                fakeNetworkModule, mapperDataModule, repositoryModule, useCasesModule))
        }
    }


    protected fun <T>printUseCaseSuccessObject(someDataObject: T) {
        println("Use case invocation: Success!")
        println(someDataObject)
    }

    protected fun <T>printUseCaseSuccessList(someList: List<T>) {
        println("Use case invocation: Success!")
        println("List size: ${someList.size}")
        println("List content:")
        someList.forEach { println(it) }
    }

    protected fun printUseCaseFailure(error: Failure) {
        println("Use case invocation: Failure :(")
        when(error) {
            is Failure.None -> throw Exception("Ups! Something went REALLY wrong. Contact support.")
            is Failure.NetworkConnectionLostSuddenly -> throw SSLException("The internet connection is suddenly lost.")
            is Failure.SSLError -> throw SSLHandshakeException("Verify the SSL.")
            is Failure.TimeOut -> throw SocketTimeoutException("Time out exception. The server took too long to answer.")
            is Failure.UnauthorizedOrForbidden -> throw Exception("Force a log out. server throw 401 - 403")
            is Failure.ServerBodyError -> throw Exception("Service Error Response (Error Body) -> CODE: ${error.code} - MESSAGE: ${error.message}")
            is Failure.DataToDomainMapperFailure -> throw IllegalArgumentException("DataToDomainMapperFailure: ${error.mapperException}")
            is Failure.ServiceUncaughtFailure -> throw Exception("500 - ServiceUncaughtFailure: ${error.uncaughtFailureMessage}")
        }
    }
}