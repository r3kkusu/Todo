package com.todo.app.ui.main.edit

import com.todo.app.data.TaskDao
import com.todo.app.ui.viewmodels.BaseViewModel
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val taskDao: TaskDao
) : BaseViewModel(taskDao) {

}