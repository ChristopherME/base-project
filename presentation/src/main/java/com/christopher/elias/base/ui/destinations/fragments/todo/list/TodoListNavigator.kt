package com.christopher.elias.base.ui.destinations.fragments.todo.list

import com.christopher.elias.base.model.TodoModel

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
interface TodoListNavigator {
    fun onTodoObjectClicked(todo: TodoModel)
}