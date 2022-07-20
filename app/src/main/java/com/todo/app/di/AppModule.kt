package com.todo.app.di

import android.app.Application
import androidx.annotation.Nullable
import androidx.room.Room
import com.todo.app.data.TodoDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        app: Application,
        callback: TodoDatabase.Callback
    ) = Room.databaseBuilder(app, TodoDatabase::class.java, "todo_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskDao(db: TodoDatabase) = db.taskDao()

    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}