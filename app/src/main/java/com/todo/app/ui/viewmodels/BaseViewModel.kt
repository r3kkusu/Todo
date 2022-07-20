package com.todo.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.data.Task
import com.todo.app.data.TaskDao
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

open class BaseViewModel constructor(
    @NotNull private val taskDao: TaskDao
    ) : ViewModel() {

    protected val taskList = MutableLiveData<List<Task>>()

    fun getTasks(completed: Boolean) {
        viewModelScope.launch {

            taskDao.getTasks(completed).collect() {
                taskList.postValue(it)
            }
        }
    }

    fun deleteTask(position: Int) {
        if (taskList.value != null || taskList.value!!.size <= position) {
            val task = taskList.value!![position]
            viewModelScope.launch {
                taskDao.delete(task)
            }
        }
    }

    fun updateTask(task: Task) {
        if (task != null) {
            viewModelScope.launch {
                taskDao.update(task)
            }
        }
    }

    fun getTaskLiveData() : LiveData<List<Task>> {
        return taskList
    }
}