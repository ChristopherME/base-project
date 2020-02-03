package com.christopher.elias.base.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.christopher.elias.base.BR
import com.christopher.elias.base.R
import com.christopher.elias.base.databinding.ItemTodoBinding
import com.christopher.elias.base.model.TodoModel

/**
 * Created by Christopher Elias on 3/02/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/

/**
 * @param items list of [TodoModel] for populate RV.
 * @param callback function that replaces the necessity of a interface for handling RV Items touch listener.
 */
class TodoAdapter(private val items: MutableList<TodoModel>,
                  val callback:(model: TodoModel, position: Int) -> Unit) : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding : ItemTodoBinding? = DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))
        return TodoHolder(binding!!)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todoModel = items[position]
        holder.setItem(model = todoModel)
        holder.itemTodoBinding.root.setOnClickListener {
            callback(todoModel, position)
        }
    }

    fun addItems(newItems: List<TodoModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class TodoHolder(val itemTodoBinding: ItemTodoBinding) : RecyclerView.ViewHolder(itemTodoBinding.root) {
        fun setItem(model: TodoModel) {
            itemTodoBinding.setVariable(BR.model, model)
            itemTodoBinding.executePendingBindings()
        }
    }
}