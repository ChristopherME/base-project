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
class TodoModelMapperImpl : TodoModelMapper {

    override suspend fun todoDomainToPresentation(todo: TodoEntity): TodoModel {
        return TodoModel(id = todo.id,
            userId = "${todo.userId}",
            title = todo.title,
            completed = todo.completed)
    }

    override suspend fun todoListDomainToPresentation(todos: List<TodoEntity>): List<TodoModel> {
        return todos.map { todo ->
            TodoModel(id = todo.id, userId = "${todo.userId}",
            title = todo.title, completed = todo.completed)
        }
    }
}