package com.christopher.elias.data.network.end_points

import com.christopher.elias.data.network.response.TodoResponse
import com.christopher.elias.data.network.utils.ConnectionUtils
import com.christopher.elias.domain.entity.Either
import com.christopher.elias.domain.entity.Either.Right
import com.christopher.elias.domain.entity.Either.Left
import com.christopher.elias.domain.entity.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class EndPointsImpl(private val endPoints: EndPointsService,
                    private val networkUtils: ConnectionUtils) : EndPoints {

    companion object {
        private const val KEY_CODE = "code"
        private const val KEY_MESSAGE = "message"
    }

    override suspend fun getTodos(): Either<Failure, List<TodoResponse>>
            = callService { endPoints.getTodos() }

    override suspend fun getTodo(todoId: Int): Either<Failure, TodoResponse>
            = callService { endPoints.getTodo(todoId) }


    /**
     * Invoke the retrofit endpoint service in IO Context and after the response has been invoked
     * verify if its successful and if has a valid body.
     */
    private suspend inline fun <T> callService(crossinline retrofitCall: suspend ()-> Response<T>) : Either<Failure, T> {
        return when(networkUtils.isNetworkAvailable()) {
            true -> {
                try {
                    withContext(Dispatchers.IO) {
                        val response = retrofitCall.invoke()
                        if(response.isSuccessful && response.body() != null) {
                            return@withContext Right(response.body()!!)
                        } else {
                            return@withContext Left(getErrorMessageFromServer(response.errorBody()?.string()))
                        }
                    }
                } catch (e: Exception) {
                    return Left(parseException(e))
                }
            }
            false -> Left(Failure.NoNetworkDetected)
        }
    }

    /**
     * Parse Server Error to [Failure.ServerBodyError] if [errorBody] [isServerErrorValid].
     * @return [Failure] object.
     */
    private suspend fun getErrorMessageFromServer(errorBody: String?) : Failure {
        return if (errorBody != null) {
            return withContext(Dispatchers.IO) {
                val serverErrorJson = JSONObject(errorBody)
                when {
                    isServerErrorValid(serverErrorJson.toString()) -> {
                        val code = serverErrorJson[KEY_CODE].toString().toInt()
                        if (code == 401 || code == 403) {
                            return@withContext Failure.UnauthorizedOrForbidden
                        } else {
                            return@withContext Failure.ServerBodyError(code, serverErrorJson[KEY_MESSAGE].toString())
                        }
                    }
                    serverErrorJson.toString().contains("\"$KEY_MESSAGE\"") -> {
                        return@withContext Failure.ServiceUncaughtFailure(serverErrorJson[KEY_MESSAGE].toString())
                    }
                    else -> return@withContext Failure.None
                }
            }
        } else {
            //No error body was found.
            Failure.None
        }
    }

    private fun isServerErrorValid(error: String) : Boolean{
        return error.contains("\"$KEY_CODE\"") && error.contains("\"$KEY_MESSAGE\"")
    }

    private fun parseException(throwable: Throwable) : Failure {
        return when(throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(throwable.message?:"Service response doesn't match with response object.")
        }
    }
}