package com.celeste.todoapp.addtasks.domain

import com.celeste.todoapp.addtasks.data.TaskRepository
import com.celeste.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class RemoveTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.remove(taskModel)
    }
}