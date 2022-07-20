package com.todo.app.ui.main.done

import com.todo.app.data.TaskDao
import com.todo.app.ui.viewmodels.BaseViewModel
import javax.inject.Inject

class DoneViewModel @Inject constructor(
    private val taskDao: TaskDao,
) : BaseViewModel(taskDao) {

    fun getCompleteTask() {
        super.getTasks(true)
    }
}