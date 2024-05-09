package com.example.taskzen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskzen.Adapter.ToDoAdapter
import com.example.taskzen.Model.ToDoModel

class Dashboard : AppCompatActivity() {

    private lateinit var taskRV: RecyclerView
    private lateinit var tasksAdapter: ToDoAdapter

    private lateinit var taskList: MutableList<ToDoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //supportActionBar?.hide()

        taskList = mutableListOf()

        taskRV = findViewById(R.id.taskRV)
        taskRV.layoutManager = LinearLayoutManager(this)

        tasksAdapter = ToDoAdapter(this) // Remove the second argument
        taskRV.adapter = tasksAdapter

        //to add dummy data
        // To add dummy data
        val task = ToDoModel()
        task.setTask("This is a test task")
        task.setStatus(0)
        task.setId(1)

        taskList.add(task)
        taskList.add(task)
        taskList.add(task)
        taskList.add(task)
        taskList.add(task)

        tasksAdapter.setTasks(taskList) // Use setTasks method to update the list in the adapter
    }
}
