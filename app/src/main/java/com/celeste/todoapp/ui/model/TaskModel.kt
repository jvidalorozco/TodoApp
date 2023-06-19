package com.celeste.todoapp.ui.model

data class TaskModel(
    val identifier: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean = false
)