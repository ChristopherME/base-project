package com.christopher.elias.base.ui.bindingtools

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christopher.elias.base.ui.adapter.TodoAdapter

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/

@BindingAdapter("todoAdapter")
fun setTodoAdapter(recyclerView: RecyclerView, adapter: TodoAdapter){
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}