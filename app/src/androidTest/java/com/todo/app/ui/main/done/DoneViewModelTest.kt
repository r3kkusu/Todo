package com.todo.app.ui.main.done

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.app.data.Task
import com.todo.app.data.TaskDao
import com.todo.app.data.TodoDatabase
import com.todo.app.utils.getOrAwaitValue
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DoneViewModelTest : TestCase() {

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
    fun testReturnFourCompletedTask() {
        val tasks = listOf(
            Task("task title 1", "task description 1", true),
            Task("task title 2", "task description 2", true),
            Task("task title 3", "task description 3", true),
            Task("task title 4", "task description 4", false),
            Task("task title 5", "task description 5", false),
            Task("task title 6", "task description 6", true)
        )

        val viewModel = DoneViewModel(taskDao)
        viewModel.insertTasks(tasks)
        viewModel.getCompleteTask()
        val count = viewModel.getTaskLiveData().getOrAwaitValue().size
        assertEquals(4, count)
    }

    @Test
    fun testDeleteAllCompletedTask() {
        val tasks = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", true),
            Task("task title 3", "task description 3", false),
            Task("task title 4", "task description 4", false),
            Task("task title 5", "task description 5", true),
            Task("task title 6", "task description 6", true)
        )

        val viewModel = DoneViewModel(taskDao)
        viewModel.insertTasks(tasks)
        viewModel.getCompleteTask()

        viewModel.getTaskLiveData().value?.forEachIndexed { i, _ ->
            viewModel.deleteTask(i)
        }


        viewModel.getCompleteTask()

        assertTrue(viewModel.getTaskLiveData().value?.isEmpty() ?: true)
    }

    @Test
    fun testUnCompleteAllTask() {
        val tasks = listOf(
            Task("task title 1", "task description 1", true),
            Task("task title 2", "task description 2", true),
            Task("task title 3", "task description 3", true)
        )

        val viewModel = DoneViewModel(taskDao)
        viewModel.insertTasks(tasks)
        viewModel.getCompleteTask()

        viewModel.getTaskLiveData().value?.forEach {
            it.completed = false
            viewModel.updateTask(it)
        }

        viewModel.getCompleteTask()

        viewModel.getTaskLiveData().value?.let { assertTrue(it.isEmpty()) }
    }
}