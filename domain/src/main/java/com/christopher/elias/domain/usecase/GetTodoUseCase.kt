package com.christopher.elias.domain.usecase

import com.christopher.elias.domain.entity.TodoEntity
import com.christopher.elias.domain.repository.TodoRepository

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
class GetTodoUseCase(private val todosRepository: TodoRepository) : BaseUseCase<TodoEntity, GetTodoUseCase.Params>() {

    override suspend fun run(params: Params) = todosRepository.getTodo(params.id)

    data class Params(val id: Int)
}