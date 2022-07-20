package com.todo.app.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.data.Task
import com.todo.app.data.TaskDao
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val taskDao: TaskDao,
) : ViewModel() {

    val taskList = MutableLiveData<List<Task>>()

    fun getUnCompleteTask() {
        viewModelScope.launch {

            taskDao.getTasks(false).collect() {
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

    fun getTaskLiveData() : LiveData<List<Task>> {
        return taskList
    }
}