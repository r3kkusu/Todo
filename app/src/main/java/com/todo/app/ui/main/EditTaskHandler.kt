package com.todo.app.ui.main

import com.todo.app.data.Task

interface EditTaskHandler {
    fun openEditFragment(task: Task?)
}