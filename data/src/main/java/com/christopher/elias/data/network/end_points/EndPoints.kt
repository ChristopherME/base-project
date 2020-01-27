package com.christopher.elias.data.network.end_points

import com.christopher.elias.data.network.response.TodoResponse
import com.christopher.elias.domain.entity.Either
import com.christopher.elias.domain.entity.Failure
import retrofit2.Response

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/

/**
 * We are using [Response] retrofit object as wrapper for every POJO we expect.
 */
interface EndPoints {

    suspend fun getTodo(todoId: Int) : Either<Failure, TodoResponse>

    suspend fun getTodos() : Either<Failure, List<TodoResponse>>
}