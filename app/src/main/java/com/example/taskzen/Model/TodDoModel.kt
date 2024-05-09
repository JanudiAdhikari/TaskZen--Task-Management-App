package com.example.taskzen.Model

class ToDoModel {
    var id: Int = 0
    var status: Int = 0
    var task: String = ""

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getStatus(): Int {
        return status
    }

    fun setStatus(status: Int) {
        this.status = status
    }

    fun getTask(): String {
        return task
    }

    fun setTask(task: String) {
        this.task = task
    }
}
