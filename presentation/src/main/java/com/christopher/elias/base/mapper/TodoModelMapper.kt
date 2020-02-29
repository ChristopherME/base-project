package com.christopher.elias.base.mapper

import com.christopher.elias.base.model.TodoModel
import com.christopher.elias.domain.entity.TodoEntity

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
interface TodoModelMapper {

    suspend fun todoDomainToPresentation(todo: TodoEntity) : TodoModel

    suspend fun todoListDomainToPresentation(todos: List<TodoEntity>) : List<TodoModel>

}