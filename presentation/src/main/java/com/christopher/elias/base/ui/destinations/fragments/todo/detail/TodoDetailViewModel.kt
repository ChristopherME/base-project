package com.christopher.elias.base.ui.destinations.fragments.todo.detail

import androidx.lifecycle.*
import com.christopher.elias.base.mapper.TodoModelMapper
import com.christopher.elias.base.model.TodoModel
import com.christopher.elias.base.ui.base.BaseViewModel
import com.christopher.elias.domain.entity.TodoEntity
import com.christopher.elias.domain.usecase.GetTodoUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by Christopher Elias on 3/02/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class TodoDetailViewModel(private val getTodoUseCase: GetTodoUseCase,
                          private val mapper: TodoModelMapper) : BaseViewModel<Any>() {

    private var _todoId = -1

    private val _todoModelFromNetwork = MutableLiveData<TodoEntity>()
    val todoModelFromNetwork : LiveData<TodoModel> = _todoModelFromNetwork.switchMap {
        liveData(Dispatchers.IO) {
            emit(mapper.todoDomainToPresentation(it))
        }
    }

    private val _todoModelFromArgs = MutableLiveData<TodoModel>()
    val todoModelFromArgs: LiveData<TodoModel>
    get() = _todoModelFromArgs

    fun refreshData() {
        setRefreshing(true)
    }

    /**
     * @param todoModel to publish new value on [_todoModelFromArgs]
     */
    fun getValuesFromArguments(todoModel: TodoModel?) {
        _todoId = todoModel?.id?:-1
        _todoModelFromArgs.value = todoModel
        executeGetTodoPerIdUseCase()
    }

    private fun executeGetTodoPerIdUseCase() {
        showLoading(true)
        val params = GetTodoUseCase.Params(_todoId)
        getTodoUseCase.invoke(viewModelScope, params){
            it.either(::handleUseCaseFailureFromBase, ::handleUseCaseSuccess)
        }
    }

    private fun handleUseCaseSuccess(todoEntity: TodoEntity) {
        _todoModelFromNetwork.value = todoEntity
        showLoading(false)
        shouldShowEmptyView(false)
        showErrorCause(false)
    }
}