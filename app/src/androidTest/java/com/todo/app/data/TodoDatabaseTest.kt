package com.todo.app.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.app.utils.containsTaskObject
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest : TestCase() {

    private lateinit var database: TodoDatabase
    private lateinit var taskDao: TaskDao

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
    fun testReturnsThreeCompletedTask() = runBlocking {

        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", true),
            Task("task title 3", "task description 3", false),
            Task("task title 4", "task description 4", true),
            Task("task title 5", "task description 5", true),
            Task("task title 6", "task description 6", false)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }


        val tasks = taskDao.getTasks(true) // returns 3 completed task

        assertEquals(3, tasks.first().size)
    }

    @Test
    fun testReturnsFourUncompletedTask() = runBlocking {

        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", false),
            Task("task title 3", "task description 3", false),
            Task("task title 4", "task description 4", true),
            Task("task title 5", "task description 5", true),
            Task("task title 6", "task description 6", false)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }


        val tasks = taskDao.getTasks(false) // returns 4 completed task

        assertEquals(4, tasks.first().size)
    }

    @Test
    fun testReturnsZeroCompletedTask() = runBlocking {

        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", false),
            Task("task title 3", "task description 3", false),
            Task("task title 4", "task description 4", false),
            Task("task title 5", "task description 5", false),
            Task("task title 6", "task description 6", false)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }


        val tasks = taskDao.getTasks(true) // returns 0 completed task

        assertEquals(0, tasks.first().size)
    }

    @Test
    fun testReturnsZeroUncompletedTask() = runBlocking {

        val tasksList = listOf(
            Task("task title 1", "task description 1", true),
            Task("task title 2", "task description 2", true),
            Task("task title 3", "task description 3", true),
            Task("task title 4", "task description 4", true),
            Task("task title 5", "task description 5", true),
            Task("task title 6", "task description 6", true)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }


        val tasks = taskDao.getTasks(false) // returns 0 completed task

        assertEquals(0, tasks.first().size)
    }

    @Test
    fun testInsertSingleTask() = runBlocking {
        val task = Task("task title", "task description", false)
        taskDao.insert(task)
        val tasks = taskDao.getTasks(false) // returns uncompleted task
        val fromDbTask = tasks.first()
        assertEquals(1, fromDbTask.size)
    }


    @Test
    fun testInsertThreeTask() = runBlocking {
        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", false),
            Task("task title 3", "task description 3", false)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }

        val tasks = taskDao.getTasks(false) // returns uncompleted task
        val fromDbTask = tasks.first()
        assertEquals(3, fromDbTask.size)
    }

    @Test
    fun testInsertFiveTask() = runBlocking {
        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", false),
            Task("task title 3", "task description 3", false),
            Task("task title 4", "task description 4", false),
            Task("task title 5", "task description 5", false),
        )

        taskDao.insertAll(tasksList)

        val tasks = taskDao.getTasks(false) // returns uncompleted task
        val fromDbTask = tasks.first()
        assertEquals(5, fromDbTask.size)
    }

    @Test
    fun testUpdateSingleTask() = runBlocking {
        val task = Task("task title", "task description", false)

        taskDao.insert(task)

        val oldTask = taskDao.getTasks(false)
        val oldTaskFirstItem = oldTask.first().first()

        oldTaskFirstItem.title = "new title"
        oldTaskFirstItem.description = "new description"
        oldTaskFirstItem.completed = true

        taskDao.update(oldTaskFirstItem)

        val newTask = taskDao.getTasks(true)
        val newTaskFirstItem = newTask.first().first()

        assertEquals(oldTaskFirstItem, newTaskFirstItem)
    }

    @Test
    fun testUpdateMultipleTask() = runBlocking {
        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", false),
            Task("task title 3", "task description 3", false)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }

        val oldTask = taskDao.getTasks(false)
        val oldTaskFirstItems = oldTask.first()

        oldTaskFirstItems.forEach {
            it.title = "new title" + it.id
            it.description = "new description" + it.id
            it.completed = true
            taskDao.update(it)
        }

        val newTask = taskDao.getTasks(true)
        val newTaskFirstItems = newTask.first()

        newTaskFirstItems.forEach {
            assertTrue(containsTaskObject(oldTaskFirstItems, it))
        }
    }


    @Test
    fun testDeleteSingleTask() = runBlocking {
        val task = Task("task title", "task description", false)

        taskDao.insert(task)

        val taskFromDb = taskDao.getTasks(false).first().first()
        taskDao.delete(taskFromDb)

        val result = taskDao.getTasks(false)

        assertTrue(result.first().isEmpty())
    }

    @Test
    fun testDeleteMultipleTask() = runBlocking {
        val tasksList = listOf(
            Task("task title 1", "task description 1", false),
            Task("task title 2", "task description 2", false),
            Task("task title 3", "task description 3", false)
        )

        tasksList.forEach {
            taskDao.insert(it)
        }

        val taskFromDb = taskDao.getTasks(false).first()
        taskFromDb.forEach {
            taskDao.delete(it)
        }

        val result = taskDao.getTasks(false)

        assertTrue(result.first().isEmpty())
    }
}