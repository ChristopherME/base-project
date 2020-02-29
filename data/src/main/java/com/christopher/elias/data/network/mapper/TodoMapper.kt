package com.christopher.elias.data.network.mapper

import com.christopher.elias.data.network.response.TodoResponse
import com.christopher.elias.domain.entity.TodoEntity

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
interface TodoMapper {

    suspend fun todoDataToDomain(todo: TodoResponse) : TodoEntity

    suspend fun todoListDataToDomain(todos: List<TodoResponse>) : List<TodoEntity>
}