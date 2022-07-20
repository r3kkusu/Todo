package com.todo.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Database(entities = [Task::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TodoDatabase>,
        @Singleton private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            // Dummy task
            applicationScope.launch {
                dao.insert(Task("Task 1", "Wash the dishes", completed = true))
                dao.insert(Task("Task 2", "Do the laundry"))
                dao.insert(Task("Task 3", "Buy groceries", completed = true))
                dao.insert(Task("Task 4", "Prepare food", completed = true))
                dao.insert(Task("Task 5", "Call mom"))
                dao.insert(Task("Task 6", "Visit grandma", completed = true))
                dao.insert(Task("Task 7", "Repair my bike"))
            }
        }
    }
}