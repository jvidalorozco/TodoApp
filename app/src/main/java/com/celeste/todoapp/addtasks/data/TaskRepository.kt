package com.celeste.todoapp.addtasks.data

import com.celeste.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map {
            TaskModel(it.id, it.task, it.selected)
        }
    }

    suspend fun add(taskModel: TaskModel){
        taskDao.insert(taskModel.toData())
    }

    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toData())
    }

    suspend fun remove(taskModel: TaskModel) {
        taskDao.removeTask(taskModel.toData())
    }
}

fun TaskModel.toData(): TaskEntity {
    return TaskEntity(this.identifier, this.task, this.selected)
}