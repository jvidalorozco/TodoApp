package com.celeste.todoapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.celeste.todoapp.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks

    fun onTaskCreated(task: String) {
        _tasks.add(TaskModel(task = task))
        _showDialog.value = false
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onCheckBoxChange(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemoved(taskModel: TaskModel) {
        val task = _tasks.find { it.identifier == taskModel.identifier }
        _tasks.remove(task)
    }
}