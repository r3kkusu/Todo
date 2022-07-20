package com.todo.app.ui.main.home

import com.todo.app.data.TaskDao
import com.todo.app.ui.viewmodels.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val taskDao: TaskDao,
) : BaseViewModel(taskDao) {

    fun getUnCompleteTask() {
        super.getTasks(false)
    }
}