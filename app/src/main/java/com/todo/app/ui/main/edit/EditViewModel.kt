package com.todo.app.ui.main.edit

import androidx.lifecycle.ViewModel
import com.todo.app.data.TaskDao
import javax.inject.Inject

class AddEditTaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

}