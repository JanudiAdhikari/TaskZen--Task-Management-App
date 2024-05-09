package com.example.taskzen.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.taskzen.Model.ToDoModel
import com.example.taskzen.R
import com.example.taskzen.Dashboard

class ToDoAdapter(private val activity: Dashboard) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private var todoList: List<ToDoModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]
        holder.task.text = item.getTask() // Use getter method to access task
        holder.task.isChecked = item.getStatus() != 0 // Use getter method to access status
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTasks(todoList: List<ToDoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task: CheckBox = itemView.findViewById(R.id.todoCheckBox)
    }
}
