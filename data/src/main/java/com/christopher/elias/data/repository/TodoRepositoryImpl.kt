package com.christopher.elias.data.repository

import com.christopher.elias.data.network.end_points.EndPoints
import com.christopher.elias.data.network.mapper.TodoMapper
import com.christopher.elias.domain.entity.Either
import com.christopher.elias.domain.entity.Failure
import com.christopher.elias.domain.entity.TodoEntity
import com.christopher.elias.domain.repository.TodoRepository

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
class TodoRepositoryImpl(private val endPoints: EndPoints,
                         private val mapper: TodoMapper) : TodoRepository {

    override suspend fun getAllTodos(): Either<Failure, List<TodoEntity>> {
        return when(val response = endPoints.getTodos()){
            is Either.Right -> Either.Right(mapper.todoListDataToDomain(response.b))
            is Either.Left -> Either.Left(response.a)
        }
    }

    override suspend fun getTodo(id: Int): Either<Failure, TodoEntity> {
        return when(val response = endPoints.getTodo(id)){
            is Either.Right -> Either.Right(mapper.todoDataToDomain(response.b))
            is Either.Left -> Either.Left(response.a)
        }
    }
}