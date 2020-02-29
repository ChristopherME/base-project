package com.christopher.elias.domain.usecase

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
class GetTodosUseCase(private val todosRepository: TodoRepository) : BaseUseCase<List<TodoEntity>, Any>() {

    override suspend fun run(params: Any): Either<Failure, List<TodoEntity>>
            = todosRepository.getAllTodos()
}