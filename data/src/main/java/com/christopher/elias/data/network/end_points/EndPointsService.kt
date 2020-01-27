package com.christopher.elias.data.network.end_points

import com.christopher.elias.data.network.response.TodoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
interface EndPointsService {

    @GET("todos")
    suspend fun getTodos() : Response<List<TodoResponse>>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") todoId: Int) : Response<TodoResponse>
}