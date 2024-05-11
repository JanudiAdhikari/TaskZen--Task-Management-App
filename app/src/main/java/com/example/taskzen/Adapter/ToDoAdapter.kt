package com.example.taskzen.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.taskzen.AddNewTask
import com.example.taskzen.Model.ToDoModel
import com.example.taskzen.R
import com.example.taskzen.Dashboard
import com.example.taskzen.Utils.DatabaseHandler

class ToDoAdapter(private val activity: Dashboard, private val db: DatabaseHandler) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private var todoList: List<ToDoModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]
        holder.task.text = item.task
        holder.task.isChecked = item.status != 0
        holder.task.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                db.updateStatus(item.id, 1)
            } else {
                db.updateStatus(item.id, 0)
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTasks(todoList: List<ToDoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        val item = todoList[position]
        db.deleteTask(item.id)
        todoList = todoList - item
        notifyItemRemoved(position)
    }

    fun editItem(position: Int) {
        val item = todoList[position]
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        bundle.putString("task", item.task)
        val fragment = AddNewTask()
        fragment.arguments = bundle
        fragment.show(activity.supportFragmentManager, AddNewTask.TAG)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task: CheckBox = itemView.findViewById(R.id.todoCheckBox)
    }
}
