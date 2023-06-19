package com.celeste.todoapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.celeste.todoapp.addtasks.data.TaskDao
import com.celeste.todoapp.addtasks.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "TaskDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(todoDatabase: TodoDatabase): TaskDao {
        return todoDatabase.taskDao()
    }
}