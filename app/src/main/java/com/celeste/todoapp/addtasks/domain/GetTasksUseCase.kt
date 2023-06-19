package com.celeste.todoapp.addtasks.domain

import com.celeste.todoapp.addtasks.data.TaskRepository
import com.celeste.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository.tasks
    }
}