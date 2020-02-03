package com.christopher.elias.base.ui.destinations.fragments.todo.list


import androidx.lifecycle.*
import com.christopher.elias.base.mapper.TodoModelMapper
import com.christopher.elias.base.model.TodoModel
import com.christopher.elias.base.ui.adapter.TodoAdapter
import com.christopher.elias.base.ui.base.BaseViewModel
import com.christopher.elias.domain.entity.TodoEntity
import com.christopher.elias.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by Christopher Elias on 3/02/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class TodoListViewModel(private val getTodosUseCase: GetTodosUseCase,
                        private val mapper: TodoModelMapper) : BaseViewModel<TodoListNavigator>() {

    /*
     * Underscore for private fields as good coding practice.
     * What we are doing here is when _todoList has a value or the current value has changed,
     * todoList listen to these changes and apply a "switchMap" which can invoke a "liveData" coroutine scope
     * an call other coroutines. Inside the liveDataScope we are calling mapper functions in Dispatchers.IO but the result will be
     * on Dispatchers.Main.
     *
     * @see https://developer.android.com/reference/androidx/lifecycle/Transformations
     */
    private val _todoList = MutableLiveData<List<TodoEntity>>()
    val todoList : LiveData<List<TodoModel>> = _todoList.switchMap {
        liveData(Dispatchers.IO) {
            emit(mapper.todoListDomainToPresentation(it))
        }
    }

    // For show the quantity of items returned by the service.
    val todoListSize : LiveData<String> = Transformations.map(_todoList) {
        "${it.size} TODO's"
    }

    // adapter consumed directly on XML through data binding.
    val adapter = TodoAdapter(arrayListOf()) { model, _ ->
        getNavigator()?.onTodoObjectClicked(todo = model)
    }

    init {
        executeGetTodoListUseCase()
    }

    /**
     * After the mapping of mapper.todoListDomainToPresentation() has finish we can populate the [adapter] of our rv.
     *
     * @param todoListMapped list from presentation layer.
     */
    fun bindItemsAfterMapping(todoListMapped: List<TodoModel>) {
        setRefreshing(false)
        showLoading(false)
        shouldShowEmptyView(todoListMapped.isEmpty())
        showErrorCause(false)
        adapter.addItems(todoListMapped)
    }

    fun refreshData() {
        setRefreshing(true)
        executeGetTodoListUseCase()
    }

    private fun executeGetTodoListUseCase() {
        showLoading(true)
        getTodosUseCase.invoke(viewModelScope,""){
            it.either(::handleUseCaseFailureFromBase,::handleUseCaseSuccess )
        }
    }

    /**
     * Fill a new value for [_todoList].
     *
     * @param todos the RIGHT side of the Either object returned from [executeGetTodoListUseCase].
     */
    private fun handleUseCaseSuccess(todos : List<TodoEntity>){
        _todoList.value = todos
    }
}