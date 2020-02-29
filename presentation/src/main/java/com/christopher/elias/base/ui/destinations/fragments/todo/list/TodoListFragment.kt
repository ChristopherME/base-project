package com.christopher.elias.base.ui.destinations.fragments.todo.list

import android.view.View
import androidx.lifecycle.Observer
import com.christopher.elias.base.BR
import com.christopher.elias.base.R
import com.christopher.elias.base.databinding.FragmentTodoListBinding
import com.christopher.elias.base.model.TodoModel
import com.christopher.elias.base.ui.base.BaseFragment
import com.christopher.elias.base.ui.destinations.fragments.todo.detail.TodoDetailFragment

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
class TodoListFragment : BaseFragment<FragmentTodoListBinding, TodoListViewModel>(TodoListViewModel::class), TodoListNavigator {

    override val getBindingVariable: Int
        get() = BR.todoListViewModel

    override val getLayoutId: Int
        get() = R.layout.fragment_todo_list

    override fun onFragmentViewReady(view: View) {
        myViewModel.setNavigator(this)
        myViewModel.todoList.observe(this, Observer {
            myViewModel.bindItemsAfterMapping(it)
        })
    }

    override fun onTodoObjectClicked(todo: TodoModel) {
        addFragment(R.id.frame_main, TodoDetailFragment.newInstance(todo), true)
    }
}