package com.todo.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.data.Task
import com.todo.app.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

open class BaseViewModel constructor(
    @NotNull private val taskDao: TaskDao
    ) : ViewModel() {

    protected val taskList = MutableLiveData<List<Task>>()

    fun getTasks(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {

            taskDao.getTasks(completed).collect() {
                taskList.postValue(it)
            }
        }
    }

    fun deleteTask(position: Int) {
        if (taskList.value != null || taskList.value!!.size <= position) {
            val task = taskList.value!![position]
            viewModelScope.launch(Dispatchers.IO) {
                taskDao.delete(task)
            }
        }
    }

    fun insertTasks(task: List<Task>) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insertAll(task)
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.update(task)
        }
    }

    fun getTaskLiveData() : LiveData<List<Task>> {
        return taskList
    }
}