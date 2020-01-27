package com.christopher.elias.domain.repository

import com.christopher.elias.domain.entity.Either
import com.christopher.elias.domain.entity.Failure
import com.christopher.elias.domain.entity.TodoEntity

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
interface TodoRepository {

    suspend fun getTodo(id: Int) : Either<Failure, TodoEntity>

    suspend fun getAllTodos() : Either<Failure, List<TodoEntity>>
}