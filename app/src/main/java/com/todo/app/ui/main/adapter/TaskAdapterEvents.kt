package com.todo.app.ui.main.adapter

import com.todo.app.data.Task

interface TaskAdapterEvents {
    fun onClickTitle(task: Task)
    fun onClickStatus(task: Task)
}