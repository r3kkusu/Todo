package com.todo.app.ui.main.edit


import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.app.data.Task
import com.todo.app.data.TaskDao
import com.todo.app.data.TodoDatabase
import com.todo.app.utils.getOrAwaitValue
import com.todo.app.utils.isEqualTaskObject
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditViewModelTest : TestCase() {

    private lateinit var database: TodoDatabase
    private lateinit var taskDao: TaskDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java).build()
        taskDao = database.taskDao()
    }

    @After
    public override fun tearDown() {
        database.close()
    }

    @Test
    fun testAddNewTask() {

        val viewModel = EditViewModel(taskDao)

        val task = Task("task title 1", "task description 1", false)

        viewModel.insertTask(task)
        viewModel.getTasks(false)


        val dbTask = viewModel.getTaskLiveData().getOrAwaitValue()
        assertTrue(dbTask.isNotEmpty() && isEqualTaskObject(dbTask.first(), task))
    }

    @Test
    fun testUpdateTaskTitleAndDescription() {

        val viewModel = EditViewModel(taskDao)
        val task = Task("task title 1", "task description 1", false)

        viewModel.insertTask(task)
        viewModel.getTasks(false)

        val dbTask = viewModel.getTaskLiveData().getOrAwaitValue().first()
        dbTask.title = "new title"
        dbTask.description = "new description"
        viewModel.updateTask(dbTask)

        val newDbTask = viewModel.getTaskLiveData().getOrAwaitValue().first()
        assertTrue(newDbTask.title == "new title" && newDbTask.description == "new description")
    }
}