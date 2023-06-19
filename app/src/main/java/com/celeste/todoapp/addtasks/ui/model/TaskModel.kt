package com.celeste.todoapp.addtasks.ui.model

data class TaskModel(
    val identifier: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)