package com.christopher.elias.domain.usecase

import com.christopher.elias.domain.entity.Either
import com.christopher.elias.domain.entity.Failure
import kotlinx.coroutines.*

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
/**
 * By convention each [BaseUseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        /*
         * Credits to Paulo.
         * https://proandroiddev.com/i-exchanged-rxjava-for-coroutines-in-my-android-application-why-you-probably-should-do-the-same-5526dfb38d0e#cf27
         *
         * Basically. All exceptions that could occur while invoking the service will be handled on EndPointImpl because
         * the response.call function is wrapped inside a try catch block. If something else occur outside of that block (like Data -> Domain mappers)
         * those exceptions will be caught here. On the Launch scope.
         */

        val backgroundJob = scope.async(Dispatchers.IO) { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }
}