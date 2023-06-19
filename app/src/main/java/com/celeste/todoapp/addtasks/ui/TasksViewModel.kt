package com.celeste.todoapp.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celeste.todoapp.addtasks.domain.AddTasksUseCase
import com.celeste.todoapp.addtasks.domain.GetTasksUseCase
import com.celeste.todoapp.addtasks.domain.RemoveTaskUseCase
import com.celeste.todoapp.addtasks.domain.UpdateTaskUseCase
import com.celeste.todoapp.addtasks.ui.model.TaskModel
import com.celeste.todoapp.addtasks.ui.model.TasksUiState
import com.celeste.todoapp.addtasks.ui.model.TasksUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    getTasksUseCase: GetTasksUseCase,
    private val addTasksUseCase: AddTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val removeTaskUseCase: RemoveTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { TasksUiState.Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TasksUiState.Loading
        )

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTasksUseCase(TaskModel(task = task))
        }
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onCheckBoxChange(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(
                taskModel.copy(selected = !taskModel.selected)
            )
        }
    }

    fun onItemRemoved(taskModel: TaskModel) {
        viewModelScope.launch {
            removeTaskUseCase(taskModel)
        }
    }
}