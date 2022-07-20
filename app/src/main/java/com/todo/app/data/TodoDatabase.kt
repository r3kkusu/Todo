package com.todo.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Database(entities = [Task::class], version = 2)
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
                dao.insert(Task("Buy groceries", "Buy groceries for mom and lola", completed = true))
                dao.insert(Task("Prepare Dinner", "1. Adobo manok\n2. Fried chicken"))
                dao.insert(Task("Take Kids to School", "July 25, 2022 kids first day in school", completed = true))
                dao.insert(Task("Car Maintenance", "1. Brakes\n2. Battery\n3. Coolant", completed = true))
                dao.insert(Task("Task 1", "Movie Night", completed = true))
                dao.insert(Task("Task 2", "Do the laundry"))
                dao.insert(Task("Task 3", "Repair my bike"))
            }
        }
    }
}