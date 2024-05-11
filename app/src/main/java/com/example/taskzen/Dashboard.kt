package com.example.taskzen

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskzen.Adapter.ToDoAdapter
import com.example.taskzen.Model.ToDoModel
import com.example.taskzen.Utils.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Collections

class Dashboard : AppCompatActivity(), DialogCloseListener {

    private lateinit var taskRV: RecyclerView
    private lateinit var tasksAdapter: ToDoAdapter
    private lateinit var db: DatabaseHandler
    private lateinit var fab: FloatingActionButton

    private lateinit var taskList: MutableList<ToDoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        db = DatabaseHandler(this)
        db.openDatabase()

        taskList = mutableListOf()

        taskRV = findViewById(R.id.taskRV)
        taskRV.layoutManager = LinearLayoutManager(this)

        tasksAdapter = ToDoAdapter(this, db) // Remove the second argument
        taskRV.adapter = tasksAdapter

        fab = findViewById(R.id.fBtn)

        val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelper(this, tasksAdapter))
        itemTouchHelper.attachToRecyclerView(taskRV)

        taskList = db.getAllTasks().toMutableList()
        taskList.reverse()
        tasksAdapter.setTasks(taskList)

        fab.setOnClickListener {
            AddNewTask.newInstance().show(supportFragmentManager, AddNewTask.TAG)
        }
    }

    override fun handleDialogClose(dialog: DialogInterface) {
        taskList = db.getAllTasks().toMutableList()
        taskList.reverse()
        tasksAdapter.setTasks(taskList)
        tasksAdapter.notifyDataSetChanged()
    }
}
