package com.celeste.todoapp.addtasks.domain

import com.celeste.todoapp.addtasks.data.TaskRepository
import com.celeste.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) {
        return taskRepository.update(taskModel)
    }
}