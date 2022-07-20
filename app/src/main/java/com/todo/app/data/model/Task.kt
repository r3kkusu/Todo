package com.todo.app.data.model

import java.time.LocalDate
import java.util.*

data class Task(
    val title: String,
    val description: String,
    val date: LocalDate,
    val isCompleted: Boolean
)
